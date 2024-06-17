package com.kaiqkt.template.domain.exceptions;

public class DomainException extends Exception{

    private ErrorType type;
    private String message;

    public DomainException(ErrorType type, String message) {
        super(message);
        this.type = type;
    }

    public DomainException(ErrorType type) {
        super(type.getMessage());
        this.type = type;
    }

    public ErrorType getType() {
        return type;
    }

    public void setType(ErrorType type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
