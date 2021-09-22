package com.es.core.exceptions;

import java.util.Map;

public class MultiErrorException extends RuntimeException {
    private final Map<?, String> errors;

    public MultiErrorException(Map<?, String> errors) {
        this.errors = errors;
    }

    public Map<?, String> getErrors() {
        return errors;
    }
}
