package com.kanesa.restapi.standard_response.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.kanesa.restapi.standard_response.model.baseResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;

public class baseController {
  // * Throw Exception Handler
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ResponseBody
  public baseResponse<String> handleValidationError(MethodArgumentNotValidException ex) {
    BindingResult result = ex.getBindingResult();
    FieldError error = result.getFieldError();
    String defaultMessage = error.getDefaultMessage();
    return new baseResponse<String>(HttpStatus.BAD_REQUEST.value(), defaultMessage, false,
        defaultMessage);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public baseResponse<String> handleExceptionError(Exception ex) {
    String message = ex.getMessage();
    return new baseResponse<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, false,
        message);
  }
}
