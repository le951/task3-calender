package org.example.task3calender.develop.validator;

import org.example.task3calender.develop.exception.PasswordValidationException;

public class PasswordValidator {

    public static void validate(String password){

        if(password.length() < 8)
            throw new PasswordValidationException("비밀번호는 8자리 이상이어야 합니다.");

    }
}
