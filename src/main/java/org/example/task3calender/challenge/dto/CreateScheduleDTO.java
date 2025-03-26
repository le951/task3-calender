package org.example.task3calender.challenge.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateScheduleDTO {

    @NotBlank(message = "작성자를 입력하세요.")
    private String writer;
    @NotBlank(message = "게시물의 비밀번호를 설정하세요.")
    private String password;

    @NotBlank(message = "일정 이름은 필수 항목입니다.")
    private String title;
    @NotNull(message = "Null 값은 허용되지 않습니다.")
    private String memo;

    @NotNull(message = "시작 시점은 필수 항목입니다.")
    @Min(value = 0, message = "1970년 1월 1일 이후로만 설정할 수 있습니다.")
    private Long startTime;

    @NotNull(message = "시작 시점은 필수 항목입니다.")
    @Min(value = 0, message = "1970년 1월 1일 이후로만 설정할 수 있습니다.")
    private Long endTime;


    // startTime < endTime
    // 만약 반대라면 강제로 동일하게 설정.
    //
    public boolean validate(){

        if(startTime > endTime) endTime = startTime;

        return true;
    }

}
