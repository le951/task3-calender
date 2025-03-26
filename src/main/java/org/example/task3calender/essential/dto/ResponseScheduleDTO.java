package org.example.task3calender.essential.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class ResponseScheduleDTO {
    private long no;

    private String title, memo;
    private String name;

    private Long startTime, endTime;
    private Long creation_time, revision_time;

}
