package com.aquinoa.ziptechtask.controllers.advices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.aquinoa.ziptechtask.exceptions.BusinessRuleException;
import com.aquinoa.ziptechtask.exceptions.InvalidDbRecordException;
import com.aquinoa.ziptechtask.exceptions.InvalidFieldException;

@ControllerAdvice
public class ApiExceptionAdvice extends ResponseEntityExceptionHandler {

  static final Logger l = LoggerFactory.getLogger(ApiExceptionAdvice.class);
  
  @ExceptionHandler(InvalidFieldException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody String handleInvalidFieldException(InvalidFieldException e) {
    l.info("Exception encountered: {}:{}", e.getField(), e.getMessage());
    return e.getMessage();
  }
  
  @ExceptionHandler(InvalidDbRecordException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public @ResponseBody String handleDbRecordException(InvalidDbRecordException e) {
    return e.getMessage();
  }
  
  @ExceptionHandler(BusinessRuleException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody String handleBusinessRuleException(BusinessRuleException e) {
    return e.getMessage();
  }
}
