package org.example.task3calender.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseScheduleDTO {
    private long no;

    private String title, memo;
    private String name;

    private Long startTime, endTime;
    private Long creation_time, revision_time;

}
