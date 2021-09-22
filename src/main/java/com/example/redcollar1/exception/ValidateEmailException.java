package com.example.redcollar1.exception;

public class ValidateEmailException extends Exception{
    public ValidateEmailException(String text){
        super("Its not email - " + text);
    }
}
