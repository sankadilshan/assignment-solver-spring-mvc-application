package com.assignment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGetDto {
    private String email;
    private byte[] image=new byte[]{};
    private String originalName;
    private String contentType;
    private boolean twoFactor;



}
