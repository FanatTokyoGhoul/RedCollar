package com.example.redcollar1.exception.advice;

import com.example.redcollar1.exception.IncorrectEmailException;
import com.example.redcollar1.exception.IncorrectNameContentException;
import com.example.redcollar1.exception.NotFoundEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(IncorrectEmailException.class)
    public ResponseEntity<String> handleException(IncorrectEmailException e) {
        return returnMessage(e);
    }

    @ExceptionHandler(IncorrectNameContentException.class)
    public ResponseEntity<String> handleException(IncorrectNameContentException e) {
        return returnMessage(e);
    }

    @ExceptionHandler(NotFoundEntityException.class)
    public ResponseEntity<String> handleException(NotFoundEntityException e) {
        return returnMessage(e);
    }

    private ResponseEntity<String> returnMessage(Exception e) {
        ResponseStatus statusAnno = e.getClass().getAnnotation(ResponseStatus.class);
        HttpStatus httpStatus = statusAnno != null ? statusAnno.value() : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(e.getMessage(), httpStatus);
    }
}
