package com.kaiqkt.template.domain.exceptions;

import org.springframework.http.HttpStatus;

public enum ErrorType {
    FOO("Error", HttpStatus.NOT_IMPLEMENTED.value());

    private String message;
    private final int code;


    ErrorType(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public ErrorType setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
