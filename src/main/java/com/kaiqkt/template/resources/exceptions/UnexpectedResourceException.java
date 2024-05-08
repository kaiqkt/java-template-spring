package com.kaiqkt.template.resources.exceptions;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Optional;

@Slf4j
public class UnexpectedResourceException extends Exception {
    private final String message;

    public UnexpectedResourceException(String message) {
        super(message);
        this.message = message;
        log.error(getLoggedMessage());
    }

    private String sourceLocation() {
        Optional<StackTraceElement> stackElement = Arrays.stream(this.getStackTrace()).findFirst();

        return stackElement.map(stackTraceElement ->
                String.format("%s:%d", stackTraceElement.getFileName(), stackTraceElement.getLineNumber()))
                .orElse(null);
    }

    private String getLoggedMessage() {
        return String.format( "Unexpected resource exception occurred at (%s): %s", sourceLocation(), message);
    }
}
