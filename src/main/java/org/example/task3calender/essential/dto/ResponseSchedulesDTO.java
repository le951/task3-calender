package org.example.task3calender.essential.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
public class ResponseSchedulesDTO {
    private long no;
    private String title;
    private Long startTime, endTime;

}
