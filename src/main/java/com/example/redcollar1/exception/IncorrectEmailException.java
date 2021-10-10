package com.example.redcollar1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectEmailException extends RuntimeException {
    public IncorrectEmailException(String text) {
        super("Its not email - " + text);
    }
}
