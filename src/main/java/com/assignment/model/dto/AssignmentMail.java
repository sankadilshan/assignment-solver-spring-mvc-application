package com.assignment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentMail {
    private String subject;
    private String description;
    private LocalDate createdDate;
    private String email;
}

