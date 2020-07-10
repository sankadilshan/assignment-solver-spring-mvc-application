package com.assignment.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandelling {
  final Logger logger = LoggerFactory.getLogger(CustomExceptionHandelling.class);

  @ExceptionHandler(SearchNotFoundException.class)
  public void handleSearchNotFoundException(HttpServletResponse response, SearchNotFoundException e) throws IOException {
    logger.error("error " + e);
    response.sendRedirect("/exception");
  }

  @ExceptionHandler(InputFailedException.class)
  public void handleInputFailedException(HttpServletResponse response, InputFailedException e) throws IOException {
    logger.error("error " + e);
    response.sendRedirect("/ioexception");
  }
}