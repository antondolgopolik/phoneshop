package com.es.phoneshop.web.controller.exceptions;

import com.es.core.exceptions.MultiErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MultiErrorException.class)
    private ResponseEntity<Map<?, ?>> handleMultiErrorException(MultiErrorException e) {
        return new ResponseEntity<>(e.getData(), HttpStatus.BAD_REQUEST);
    }
}
