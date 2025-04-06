package org.example.task3calender.develop.controller;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.task3calender.develop.dto.request.CreateUserRequestDto;
import org.example.task3calender.develop.dto.request.LoginRequestDto;
import org.example.task3calender.develop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    private final UserService userService;

    UserController(UserService userService){ this.userService = userService; }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody @Valid CreateUserRequestDto dto
    ){
        userService.createUser(dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public String login(
            @RequestBody @Valid LoginRequestDto dto,
            HttpSession session // HttpSession 사용 시 session cookie 자동 생성
    ){

        // 로그인 인증. 실패 시 throw 하여 Request 중단
        Long userId = userService.login(dto);

        session.setAttribute("userId", userId);

        // Redirect Main
        return "/";
    }


}
