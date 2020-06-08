package com.assignment.repository;

import com.assignment.model.Assignment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AssignmentRepository extends MongoRepository<Assignment,Long> {

    List<Assignment> findBySubject(String subject);
}
