package com.meli.itemdetail.core.domain.dto;

import java.util.List;

public class ErrorResponse {
    private String message;
    private String error;
    private int status;
    private List<Cause> cause;

    public ErrorResponse() {}

    public ErrorResponse(String message, String error, int status, List<Cause> cause) {
        this.message = message;
        this.error = error;
        this.status = status;
        this.cause = cause;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Cause> getCause() {
        return cause;
    }

    public void setCause(List<Cause> cause) {
        this.cause = cause;
    }
}
