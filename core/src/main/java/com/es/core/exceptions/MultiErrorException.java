package com.es.core.exceptions;

import java.util.Map;

public class MultiErrorException extends RuntimeException {
    protected Map<?, ?> data;

    protected MultiErrorException() {
    }

    public MultiErrorException(Map<?, ?> data) {
        this.data = data;
    }

    public Map<?, ?> getData() {
        return data;
    }
}
