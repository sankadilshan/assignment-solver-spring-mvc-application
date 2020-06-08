package com.assignment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "assignment")
public class Assignment {
    @Transient
    public static final String SEQUENCE_NAME = "assignment_sequence";

    @Id
    private long assignmentId;
    @NonNull
    private String subject;
    private String description;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd", style = "SS", iso = DateTimeFormat.ISO.NONE)
    private LocalDate endDate;
    @Lob
    private byte[] assignmentFile;
    private String originalName;
    private String contentType;
    @Temporal(TemporalType.DATE )
    @DateTimeFormat(pattern = "yyyy-MM-dd", style = "SS", iso = DateTimeFormat.ISO.NONE)
    private LocalDate createDate = LocalDate.now();
    @NonNull
    private String username; //we put here email


}
