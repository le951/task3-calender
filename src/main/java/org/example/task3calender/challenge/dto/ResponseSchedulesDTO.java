package org.example.task3calender.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseSchedulesDTO {
    private long no;
    private String title;
    private Long startTime, endTime;

}
