package org.example.task3calender.develop.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;


// Login Response가 많아지는 경우 해당 DTO로 변경.
// 현재 기준 매개변수로 userId만 전달하여 Session 저장

@NoArgsConstructor
@Getter
public class LoginServiceDto {
    private String username;
}
