package com.assignment.service;

import com.assignment.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private AssignmentService assignmentService;

    public boolean send(Mail mail){
        AssignmentMail toAddress = findToAddress(mail.getAssignmentId());
        String subject="REQUIRED ASSIGNMENT NO: "+mail.getAssignmentId() + "SUBJECT: "+toAddress.getSubject().toUpperCase();
        String body="Hi "+toAddress.getEmail().split("@")[0].toString()+"\n\n"+
                "Assignment id: "+mail.getAssignmentId()+"\n"+
                "Subject: "+toAddress.getSubject()+"\n"+
                "Description: "+toAddress.getDescription()+"\n"+
                "Upload date: "+toAddress.getCreatedDate().toString()+"\n\n"+
                mail.getMsg()+"\n\n"+
                userService.getCurrentUser().getEmail().split("@")[0].toString()+"\n"+
                "Thank You "+"\n"+
                "This message delivered by Assignment Solver platform. Don't reply and contact through details in mail ";
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("sankadilshan9686@gmail.com");
            message.setCc("sanka1012@outlook.com");
            message.setSubject(subject);
            message.setText(body);
            message.setSentDate(new Date());
            mailSender.send(message);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    private AssignmentMail findToAddress(long assignmentId) {
//        return restTemplate.getForObject("http://localhost:9001/user/address/" + assignmentId, AssignmentMail.class);
          return  assignmentService.findByAssignmentId(assignmentId);
    }



}
