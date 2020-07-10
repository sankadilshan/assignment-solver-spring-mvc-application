package com.assignment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentMultipart {
    private long id;
    private String fileName;
    private String fileType;
    private byte[] file;

}

