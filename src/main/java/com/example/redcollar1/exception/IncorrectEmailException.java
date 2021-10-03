package com.example.redcollar1.exception;

public class IncorrectEmailException extends RuntimeException {
    public IncorrectEmailException(String text) {
        super("Its not email - " + text);
    }
}
