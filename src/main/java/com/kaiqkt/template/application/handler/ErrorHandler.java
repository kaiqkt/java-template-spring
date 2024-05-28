package com.kaiqkt.template.application.handler;

import com.kaiqkt.template.domain.exceptions.DomainException;
import com.kaiqkt.template.generated.application.dto.ErrorV1;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
class ErrorHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            FieldError fieldError = (FieldError) error;
            String name = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            errors.put(name, message);
        });

        ErrorV1 error = new ErrorV1("INVALID_ARGUMENTS", errors);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorV1> handleGenericException(Exception ex, WebRequest request) {
        ErrorV1 error = new ErrorV1("INTERNAL_ERROR", ex.getMessage());

        logger.error("Internal error: ", ex);

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorV1> handleDomainException(DomainException ex, WebRequest request) {
        ErrorV1 error = new ErrorV1(ex.getType().name(), ex.getType().getMessage());

        logger.error("Domain error: ", ex);

        return new ResponseEntity<>(error, HttpStatusCode.valueOf(ex.getType().getCode()));
    }
}
