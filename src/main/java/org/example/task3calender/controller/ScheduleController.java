package org.example.task3calender.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.task3calender.dto.request.CreateScheduleRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class ScheduleController {


    @PostMapping("/")
    public ResponseEntity<?> createSchedule(
            @RequestBody @Valid CreateScheduleRequestDto dto,
            HttpSession session
    ){



        return new ResponseEntity<>(HttpStatus.OK);
    }

}
