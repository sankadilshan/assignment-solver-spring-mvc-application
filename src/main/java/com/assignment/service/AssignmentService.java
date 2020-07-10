package com.assignment.service;

import com.assignment.exception.InputFailedException;
import com.assignment.exception.SearchNotFoundException;
import com.assignment.model.Assignment;
import com.assignment.model.dto.AssignmentDto;
import com.assignment.model.dto.AssignmentMail;
import com.assignment.model.dto.AssignmentMultipart;
import com.assignment.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssignmentService implements Compress {
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private UserService userService;

    public List<Assignment> getAllAssignments() {
        Sort createDate = Sort.by("createDate").ascending();
        return assignmentRepository.findAll(createDate);
    }

    @Transactional
    public boolean initiateAssignment(AssignmentDto assignment, MultipartFile file) throws InputFailedException {
        try{
        Assignment assignment1 = new Assignment();
        assignment1.setSubject(assignment.getSubject());
        assignment1.setDescription(assignment.getDescription());
        assignment1.setEndDate(assignment.getEndDate());
        assignment1.setUsername(userService.getCurrentUser().getEmail());
        assignment1.setAssignmentFile(file.getBytes());
        assignment1.setOriginalName(file.getOriginalFilename());
        assignment1.setContentType(file.getContentType());
        assignmentRepository.save(assignment1);
        }
        catch (Exception e){
          throw new InputFailedException();
        }
        return true;
    }

    public AssignmentMultipart downloadFile(long id) throws SearchNotFoundException {
        Optional<Assignment> assignment = assignmentRepository.findById(id);
        assignment.orElseThrow(SearchNotFoundException::new);
        return new AssignmentMultipart(assignment.get().getAssignmentId(), assignment.get().getOriginalName(), assignment.get().getContentType(), assignment.get().getAssignmentFile());
    }

    public long getAllAssignmentsCount() {
        return assignmentRepository.findAll().stream().mapToLong(Assignment::getAssignmentId).count();
    }

    public List<String> getSubjects() {
        try {
            return assignmentRepository.findAll().stream().map(Assignment::getSubject).collect(Collectors.toList());
        }catch (SearchNotFoundException e){
            throw new SearchNotFoundException();
        }
    }

    public List<Assignment> findBySubject(String subject)throws SearchNotFoundException {
        List<Assignment> bySubject = assignmentRepository.findBySubject(subject);
        bySubject.forEach(assignment -> {
            System.out.println(assignment.getSubject());
        });
        return bySubject;

    }

    public AssignmentMail findByAssignmentId(long assignmentID) {
        Optional<Assignment> assignment = assignmentRepository.findById(assignmentID);
        assignment.orElseThrow(SearchNotFoundException::new);
        Assignment assign = assignment.get();
        return new AssignmentMail(assign.getSubject(), assign.getDescription(), assign.getCreateDate(), assign.getUsername());
    }
}
