package com.api.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFound(
            RuntimeException exc,
            WebRequest req
    ) {
        String bodyResponse = "This is a bad request, cause by:" + exc.getMessage();
        return handleExceptionInternal(exc, bodyResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, req);
    }
}
