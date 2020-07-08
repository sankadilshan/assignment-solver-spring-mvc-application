package com.assignment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
@Component
public class TwoFactorAuthentication {
    private final Logger logger= LoggerFactory.getLogger(TwoFactorAuthentication.class);
    @Autowired
    private MailService mailService;
    private long twoFactorAuthemticationCode;
    private LocalDateTime localDateTime;
    public boolean generated=false;


 public void genereteAuthenticationCode()throws Exception{
         Random random = new Random();
         twoFactorAuthemticationCode = random.nextInt(900000) + 100000;
         logger.info("code- 2factorauthentication " + twoFactorAuthemticationCode);
         mailService.sendTwoFactorAuthenticationCode(twoFactorAuthemticationCode);
        callTimer();
     }


    public void callTimer() {
        localDateTime=LocalDateTime.now();
        logger.info("date "+localDateTime);
        logger.info("after 5"+localDateTime.plusMinutes(5));
        generated=true;
    }

//    private setTimeForCodeValidation(){
//
//    }

    public boolean checkCodeValidation(long code)throws Exception{
     if (code==twoFactorAuthemticationCode && localDateTime.isBefore(localDateTime.plusMinutes(5))){
         return true;
     }
     return false;
 }

}
