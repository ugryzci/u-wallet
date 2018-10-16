package com.uguryazici.uwallet.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {PlayerNotFoundException.class})
    protected ResponseEntity<Object> handlePlayerNotFound(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Player Not Found.";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value
            = { AccountNotFoundException.class})
    protected ResponseEntity<Object> handleAccountNotFound(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Account Not Found.";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value
            = { InsufficientBalanceException.class})
    protected ResponseEntity<Object> handleInsufficientBalance(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Insufficient Balance!";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }
}
