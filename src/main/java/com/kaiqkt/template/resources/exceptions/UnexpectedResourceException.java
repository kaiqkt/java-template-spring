package com.kaiqkt.template.resources.exceptions;

import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Optional;

public class UnexpectedResourceException extends Exception {
    private final String message;

    public UnexpectedResourceException(String message) {
        super(message);
        this.message = message;
        LoggerFactory.getLogger(UnexpectedResourceException.class).error(getLoggedMessage());
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
