package com.assignment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class TwoFactorAuthentication {
    private final Logger logger= LoggerFactory.getLogger(TwoFactorAuthentication.class);
    @Autowired
    private MailService mailService;
    private long twoFactorAuthemticationCode;

 public void genereteAuthenticationCode()throws Exception{
     Random random=new Random();
      twoFactorAuthemticationCode = random.nextInt(900000)+100000;
      logger.info("code- 2factorauthentication "+twoFactorAuthemticationCode);
      mailService.sendTwoFactorAuthenticationCode(twoFactorAuthemticationCode);
 }

 public boolean checkCodeValidation(long code)throws Exception{
     if (code==twoFactorAuthemticationCode){
         return true;
     }
     return false;
 }

}
