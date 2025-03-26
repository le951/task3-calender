package org.example.task3calender.challenge.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.task3calender.challenge.dto.*;
import org.example.task3calender.challenge.service.SchedulerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class Controller {

    private final SchedulerService service;

    public Controller(SchedulerService schedulerService){
        this.service = schedulerService;
    }


    @PostMapping()
    public ResponseEntity<Long> createSchedule(
            @RequestBody @Valid CreateScheduleDTO dto
    ){
        var response = service.createSchedule(dto);

        if(response == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/list")
    public ResponseEntity<List<ResponseSchedulesDTO>> getSchedules(
            @RequestParam(required = false) String writer,
            @RequestParam(required = false) Long revisionStart,
            @RequestParam(required = false) Long revisionEnd,
            @RequestParam(required = false) Long scheduleStart,
            @RequestParam(required = false) Long scheduleEnd
    ){
        var dto = new RequestSchedulesDTO(writer, revisionStart, revisionEnd, scheduleStart, scheduleEnd);
        var response = service.getSchedules(dto);

        if(response == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{no}")
    public ResponseEntity<ResponseScheduleDTO> getSchedule(
            @PathVariable long no
    ){

        var response = service.getSchedule(no);

        if(response == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(service.getSchedule(no), HttpStatus.OK);
    }

    @PatchMapping()
    public ResponseEntity<Boolean> patchSchedule(
            @RequestBody @Valid PatchScheduleDTO dto
    ){

        var response = service.patchSchedule(dto);

        if(response==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<DeleteScheduleDTO> deleteSchedule(
            @RequestBody @Valid DeleteScheduleDTO dto
    ){

        // true : 성공 | false : 실패 (비밀번호 오류)
        // null : 존재하지 않는 no

        Boolean response = service.deleteSchedule(dto);

        if(response == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(response) return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


}







