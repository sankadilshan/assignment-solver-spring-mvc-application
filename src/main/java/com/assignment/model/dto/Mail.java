package com.assignment.model.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class Mail {
    @NonNull
    private long  assignmentId;
    @NonNull
    private String msg;
    private String contact;
}
