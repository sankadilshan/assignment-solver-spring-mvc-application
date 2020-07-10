package com.assignment.service;

import com.assignment.exception.SearchNotFoundException;
import com.assignment.model.dto.AssignmentMail;
import com.assignment.model.dto.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailService {

    private final Logger logger = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private AssignmentService assignmentService;
    @Autowired
    private TemplateEngine templateEngine;

    public boolean send(Mail mail) {
        AssignmentMail toAddress = findToAddress(mail.getAssignmentId());
        String currentUserEmail = userService.getCurrentUser().getEmail();
        String subject = "REQUIRED ASSIGNMENT NO: " + mail.getAssignmentId() + "SUBJECT: " + toAddress.getSubject().toUpperCase();
        String body = "<b> Hi " + toAddress.getEmail().split("@")[0] + "</b>\n\n" +
                "Assignment id: " + mail.getAssignmentId() + "\n" +
                "Subject: " + toAddress.getSubject() + "\n" +
                "Description: " + toAddress.getDescription() + "\n" +
                "Upload date: " + toAddress.getCreatedDate().toString() + "\n\n" +
                mail.getMsg() + "\n\n" +
                currentUserEmail.split("@")[0] + "\n" +
                "Thank You " + "\n" +
                "This message delivered by Assignment Solver platform. Don't reply and contact through details in mail ";
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toAddress.getEmail());
            message.setCc(currentUserEmail);
            message.setSubject(subject);
            message.setText(body);
            message.setSentDate(new Date());
            mailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean sendTwoFactorAuthenticationCode(long code) {
        Context context = new Context();

        try {
            String currentUserEmail = userService.getCurrentUser().getEmail();
            String subject = "VERIFICATION CODE- ASSIGNMENT SOLVER";
            logger.info("current user mail " + currentUserEmail);

            Map<String, Object> model = new HashMap<String, Object>();
            model.put("to", currentUserEmail);
            model.put("code", code);
            context.setVariables(model);
            String body = templateEngine.process("email", context);
            final MimeMessage mimeMessage = mailSender.createMimeMessage();
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
            message.setTo(currentUserEmail);
            message.setSubject(subject);
            message.setSentDate(new Date());
            message.setText(body, true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private AssignmentMail findToAddress(long assignmentId) {
        return assignmentService.findByAssignmentId(assignmentId);
    }


}
