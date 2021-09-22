package com.example.redcollar1.services;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidatorService {
    public boolean validateEmail(String email){
        Pattern pattern = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
