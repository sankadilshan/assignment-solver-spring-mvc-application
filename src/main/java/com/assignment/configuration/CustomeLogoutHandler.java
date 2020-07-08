package com.assignment.configuration;

import com.assignment.service.TwoFactorAuthentication;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class CustomeLogoutHandler implements LogoutHandler {
    @Autowired
    private TwoFactorAuthentication twoFactorAuthentication;
    @SneakyThrows
    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        twoFactorAuthentication.generated=false;
        httpServletResponse.sendRedirect("/");
    }
}
