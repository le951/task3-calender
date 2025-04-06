package org.example.task3calender.develop.exception;

import jakarta.persistence.PersistenceException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Arrays;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handlerMissingServletRequestParameter(MissingServletRequestParameterException e){
        log.warn(e.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Request Parameter가 잘못되었습니다.");
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> handlerNoResourceFound(NoResourceFoundException e){
        log.warn(e.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("잘못된 경로의 요청입니다.");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArguemnt(IllegalArgumentException e){
        log.warn(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(PasswordValidationException.class)
    public ResponseEntity<String> handlePasswordValidation(PasswordValidationException e){
        log.warn(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }


    // DB 관련 Exceptions
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("잘못된 데이터 입력입니다.");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handlerConstraintViolation(ConstraintViolationException e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("잘못된 데이터 입력입니다.");
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<String> handlerPersistence(PersistenceException e){
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("잘못된 데이터 입력입니다.");
    }




    // 알 수 없는 예외 발생 시 Request 중단
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerException(Exception e){
//        Arrays.stream(e.getStackTrace()).iterator().forEachRemaining(s -> {
//            String st = s.getFileName() + " " +
//                    s.getClassName() + " " +
//                    s.getMethodName() + " " +
//                    s.getLineNumber() + " ";
//
//            log.error(st);
//        });
        log.error(e.toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("요청 또는 서버에 문제가 있습니다.");
    }


}
