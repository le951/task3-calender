package org.example.task3calender.challenge.service;


import org.example.task3calender.challenge.dto.*;

import java.util.List;

public interface SchedulerService {

    Long createSchedule(CreateScheduleDTO dto);

    ResponseScheduleDTO getSchedule(long l);

    List<ResponseSchedulesDTO> getSchedules(RequestSchedulesDTO dto);

    Boolean patchSchedule(PatchScheduleDTO dto);

    Boolean deleteSchedule(DeleteScheduleDTO dto);
}

