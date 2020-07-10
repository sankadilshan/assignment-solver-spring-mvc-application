package com.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Lob;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class User {
    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";

    @Id
    private Long userId;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @Lob
    private byte[] image;
    private String originalName;
    private String contentType;
    private boolean twoFactor;

}
