package org.example.task3calender.essential.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.task3calender.essential.dto.CreateScheduleDTO;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    private long no;
    private String title;
    private String memo;

    private Timestamp start_time, end_time;

    private String writer;
    private String password;

    private Timestamp creation_time, revision_time;

//    public Schedule(CreateScheduleDTO dto){
//        title = dto.getTitle();
//        memo = dto.getMemo();
//        start_time = dto.getStartTIme();
//        end_time = dto.getEndTime();
//        writer = dto.getWriter();
//        password = dto.getPassword();
//    }

}
