package com.kaiqkt.template.domain.exceptions;

import lombok.Getter;

@Getter
public enum ErrorType {
    FOO("Error", 404);

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
}
