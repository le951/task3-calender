package org.example.task3calender.develop.controller;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpSession;
import org.example.task3calender.develop.dto.request.CreateScheduleRequestDto;
import org.example.task3calender.develop.entity.Schedule;
import org.example.task3calender.develop.entity.User;
import org.example.task3calender.develop.service.TestService;
import org.example.task3calender.develop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class TestController {

    @PersistenceContext
    private EntityManager em;

    private final TestService testService;
    private final UserService userService;

//    private final PasswordEncoder hashEncoder = new BCryptPasswordEncoder();


    TestController(TestService testService, UserService userService) {
        this.testService = testService;
        this.userService = userService;
    }

    @Transactional
    @GetMapping("/test")
    public ResponseEntity<?> test(){

        var user = new User("account1", "Password1");

        var schedule = new Schedule(user, LocalDateTime.now(), LocalDateTime.now(), "Schedule Title", "Details");

        em.persist(user);
        em.persist(schedule);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/test2")
    public ResponseEntity<?> test2(
            @RequestBody CreateScheduleRequestDto dto,
            HttpSession session
            ){

        return new ResponseEntity<>(HttpStatus.OK);
    }



}
