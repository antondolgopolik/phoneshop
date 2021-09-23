package com.es.core.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class ValidationException extends MultiErrorException {

    public ValidationException(Map<?, ?> data) {
        super(data);
    }

    public ValidationException(MessageSource messageSource, BindingResult bindingResult) {
        HashMap<String, String> map = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String code = fieldError.getCode();
            Object[] args = new Object[]{fieldError.getField()};
            String message = messageSource.getMessage(Objects.requireNonNull(code), args, Locale.ENGLISH);
            map.put(fieldError.getField(), message);
        }
        data = map;
    }
}
