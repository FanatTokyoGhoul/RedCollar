package com.example.redcollar1.services.validation;

import common.lib.exception.IncorrectEmailException;
import common.lib.exception.IncorrectNameContentException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckData {

    static final int MIN_LENGTH_NAME = 4;
    static final int MAX_LENGTH_NAME = 100;

    public static void validateEmail(String email) {
        Pattern pattern = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IncorrectEmailException(email);
        }
    }

    public static void validateNameContent(String name) {
        if (name.length() < MIN_LENGTH_NAME || name.length() > MAX_LENGTH_NAME) {
            throw new IncorrectNameContentException();
        }
    }
}
