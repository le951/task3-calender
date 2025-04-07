package org.example.task3calender.service;


import org.example.task3calender.dto.request.CreateScheduleRequestDto;
import org.example.task3calender.entity.Schedule;
import org.example.task3calender.entity.User;
import org.example.task3calender.repository.ScheduleRepository;
import org.example.task3calender.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepo;
    private final UserRepository userRepo;


    ScheduleService(ScheduleRepository scheduleRepo, UserRepository userRepo){
        this.scheduleRepo = scheduleRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public void createSchedule(CreateScheduleRequestDto dto, Long userId){

        // get 실패 시 throw EntityNotFoundException 됨.
        User user = userRepo.getReferenceById(userId);

        Schedule schedule = new Schedule(user, dto.getStartAt(), dto.getEndAt(), dto.getTitle(), dto.getDetail());

        scheduleRepo.save(schedule);

    }

}
