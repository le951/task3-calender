package org.example.task3calender.develop.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class CreateScheduleRequestDto {

    private String title;

    private String detail;

    private LocalDateTime startAt;

    private LocalDateTime endAt;



}
