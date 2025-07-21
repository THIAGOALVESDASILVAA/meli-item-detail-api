package com.meli.itemdetail.core.domain.exception;

import java.util.List;

public class NotFoundException extends RuntimeException {
    private final String errorCode;
    private final List<String> fieldReference;

    public NotFoundException(String errorCode, List<String> fieldReference, String message) {
        super(message);
        this.errorCode = errorCode;
        this.fieldReference = fieldReference;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public List<String> getFieldReference() {
        return fieldReference;
    }
}
