package org.example.task3calender.essential.service;


import org.example.task3calender.essential.dto.*;

import java.util.List;

public interface SchedulerService {

    Long createSchedule(CreateScheduleDTO dto);

    ResponseScheduleDTO getSchedule(long l);

    List<ResponseSchedulesDTO> getSchedules(RequestSchedulesDTO dto);

    Boolean patchSchedule(PatchScheduleDTO dto);

    Boolean deleteSchedule(DeleteScheduleDTO dto);
}

