package org.example.task3calender.essential.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class PatchScheduleDTO {
    private Long no;

    private String password;

    private String writer;

    private String title, memo;

    private Long startTime, endTime;
}
