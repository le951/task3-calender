package org.example.task3calender.essential.repository;


import org.example.task3calender.essential.dto.*;

import java.util.List;

public interface SchedulerRepository {

    long createSchedule(CreateScheduleDTO dto);

    List<ResponseSchedulesDTO> getSchedules(RequestSchedulesDTO dto);

    ResponseScheduleDTO getSchedule(long l);

    Boolean patchSchedule(PatchScheduleDTO dto);

    Boolean deleteSchedule(DeleteScheduleDTO dto);


}
