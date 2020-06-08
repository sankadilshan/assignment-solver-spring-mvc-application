package com.assignment.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.util.Date;

@Data
public class AssignmentDto {
    private String subject;
    private String description;
    @Temporal(TemporalType.DATE)
   @DateTimeFormat(pattern = "yyyy-MM-dd", style = "SS", iso = DateTimeFormat.ISO.NONE)
    private LocalDate endDate;

}
