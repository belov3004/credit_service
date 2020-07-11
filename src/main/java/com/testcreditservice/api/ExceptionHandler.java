package com.testcreditservice.api;


import com.testcreditservice.api.exceptions.AlreadyExistException;
import com.testcreditservice.api.exceptions.CreditLimitException;
import com.testcreditservice.api.exceptions.NotFoundException;
import com.testcreditservice.api.exceptions.NotValidException;
import com.testcreditservice.model.ApiErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ApiErrorDto> authenticationException(AlreadyExistException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.valueOf(e.getCode())).body(new ApiErrorDto(e.getCode(), e.getLocalizedMessage(), "", request.getRequestURI()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorDto> authenticationException(NotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorDto(404, e.getLocalizedMessage(), "", request.getRequestURI()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NotValidException.class)
    public ResponseEntity<ApiErrorDto> authenticationException(NotValidException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.valueOf(e.getCode())).body(new ApiErrorDto(e.getCode(), e.getLocalizedMessage(), "", request.getRequestURI()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CreditLimitException.class)
    public ResponseEntity<ApiErrorDto> authenticationException(CreditLimitException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.valueOf(e.getCode())).body(new ApiErrorDto(e.getCode(), e.getLocalizedMessage(), "", request.getRequestURI()));
    }

}
