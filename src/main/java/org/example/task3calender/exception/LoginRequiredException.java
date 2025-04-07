package org.example.task3calender.exception;

public class LoginRequiredException extends RuntimeException {

    public LoginRequiredException(String message){
        super(message);
    }

}
