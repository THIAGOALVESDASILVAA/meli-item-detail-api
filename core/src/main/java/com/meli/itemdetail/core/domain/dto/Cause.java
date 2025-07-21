package com.meli.itemdetail.core.domain.dto;

import java.util.List;

public class Cause {
    private String department;
    private int cause_id;
    private String type;
    private String code;
    private List<String> references;
    private String message;

    public Cause() {}

    public Cause(String department, int cause_id, String type, String code, List<String> references, String message) {
        this.department = department;
        this.cause_id = cause_id;
        this.type = type;
        this.code = code;
        this.references = references;
        this.message = message;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getCause_id() {
        return cause_id;
    }

    public void setCause_id(int cause_id) {
        this.cause_id = cause_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getReferences() {
        return references;
    }

    public void setReferences(List<String> references) {
        this.references = references;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
