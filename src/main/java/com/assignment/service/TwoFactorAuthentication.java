package com.assignment.service;

import com.assignment.exception.InputFailedException;
import com.assignment.exception.SearchNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class TwoFactorAuthentication {
    private final Logger logger = LoggerFactory.getLogger(TwoFactorAuthentication.class);
    public boolean generated = false;
    @Autowired
    private MailService mailService;
    private long twoFactorAuthemticationCode;
    private LocalDateTime localDateTime;

    public void genereteAuthenticationCode() throws SearchNotFoundException {
        Random random = new Random();
        twoFactorAuthemticationCode = random.nextInt(900000) + 100000;
        logger.info("generated two factor authentication ");
        mailService.sendTwoFactorAuthenticationCode(twoFactorAuthemticationCode);
        callTimer();
    }


    public void callTimer() {
        localDateTime = LocalDateTime.now();
        logger.info(localDateTime.toString());
        logger.info(localDateTime.plusMinutes(5).toString());
        generated = true;
    }


    public boolean checkCodeValidation(long code) throws InputFailedException {
        if (code == twoFactorAuthemticationCode && localDateTime.isBefore(localDateTime.plusMinutes(5))) {
            return true;
        }
        return false;
    }

}
