package com.example.redcollar1.services;

import com.example.redcollar1.exception.IncorrectEmailException;
import com.example.redcollar1.exception.IncorrectNameContentException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    static final int MIN_LENGTH_EMAIL = 4;
    static final int MAX_LENGTH_EMAIL = 100;

    public static void validateEmail(String email) {
        Pattern pattern = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IncorrectEmailException(email);
        }
    }

    public static void validateNameContent(String name) {
        if (name.length() < MIN_LENGTH_EMAIL || name.length() > MAX_LENGTH_EMAIL) {
            throw new IncorrectNameContentException();
        }
    }
}
