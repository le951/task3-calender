package org.example.task3calender.essential.service;

import org.example.task3calender.essential.dto.*;
import org.example.task3calender.essential.repository.SchedulerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    private final SchedulerRepository repo;

    public SchedulerServiceImpl(SchedulerRepository schedulerRepository){
        this.repo = schedulerRepository;
    }

    @Override
    public Long createSchedule(CreateScheduleDTO dto) {

        // 유효성 검증
        // 1차 : DTO와 @Valid, @NotBlank @Size 등
        // 2차 : Service Layer. dto.validateForCreate()
        // 3차 : Database TABLE 제약 조건. NOT NULL, DEFAULT, UNIQUE 등

        if(dto.validate()){
            return repo.createSchedule(dto);
        }

        // 오류 Response 반환할 것.
        return null;

    }

    @Override
    public ResponseScheduleDTO getSchedule(long l) {
        return repo.getSchedule(l);
    }

    @Override
    public List<ResponseSchedulesDTO> getSchedules(RequestSchedulesDTO dto) {

        // 유효성 검사 & 교정
        dto.validate();

        return repo.getSchedules(dto);
    }

    @Override
    public Boolean patchSchedule(PatchScheduleDTO dto) {

        return repo.patchSchedule(dto);
    }

    public Boolean deleteSchedule(DeleteScheduleDTO dto){
        return repo.deleteSchedule(dto);
    }
}
