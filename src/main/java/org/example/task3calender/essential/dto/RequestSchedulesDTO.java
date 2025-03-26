package org.example.task3calender.essential.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestSchedulesDTO {

    private String writer;
    private Long revisionStart, revisionEnd;
    private Long scheduleStart, scheduleEnd;


    public boolean validate(){

        // 시나리오
        // 1. Default 검색 : Start = null; End = null;
        // 2. 특정 시점 이후 검색 : Start = x; End = null;
        // 3. 특정 시점 이전 검색 : Start = null; End = x;

        // Timestamp 사용으로 날짜는 00:00:00을 기준으로 한다. 1970-01-01 이전 = 00:00:00 이전

        // 허용되지 않는 경우
        // Start와 End 모두 !null 이면서 Start가 End보다 값이 큰 경우.

        if((revisionStart != null) & (revisionEnd != null)) {
            if(revisionStart > revisionEnd) revisionEnd = revisionStart;
        }

        if((scheduleStart != null) & (scheduleEnd != null)) {
            if(scheduleStart > scheduleEnd) scheduleEnd = scheduleStart;
        }

        return true;
    }

}
