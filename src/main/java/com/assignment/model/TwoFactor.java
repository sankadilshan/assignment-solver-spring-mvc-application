package com.assignment.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Timer;

@Component
@Data
public class TwoFactor {
    private long code;
    private boolean isGenerated;
    private Timer timer;
}
