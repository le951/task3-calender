package org.example.task3calender.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PatchScheduleDTO {
    private Long no;

    private String password;

    private String writer;

    private String title, memo;

    private Long startTime, endTime;
}
