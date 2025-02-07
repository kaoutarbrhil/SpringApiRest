package com.api.springapirest.exception;

import com.api.springapirest.model.ErrorMessage;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.Map;

@Hidden
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handlerNotFound(NotFoundException ex){
        return new ResponseEntity<>(
                ErrorMessage.builder().message(Map.of("Error", ex.getMessage()))
                        .details("")
                        .timestamp(new Date())
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }
}
