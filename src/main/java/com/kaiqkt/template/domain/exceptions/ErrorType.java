package com.kaiqkt.template.domain.exceptions;

public enum ErrorType {
    FOO("Error");

    public final String message;

    ErrorType(String message) {
        this.message = message;
    }
}
