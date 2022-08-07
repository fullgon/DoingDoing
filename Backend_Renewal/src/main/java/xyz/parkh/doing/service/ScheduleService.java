package xyz.parkh.doing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.entity.schedule.Schedule;
import xyz.parkh.doing.domain.entity.schedule.ToDoSchedule;
import xyz.parkh.doing.domain.model.schedule.ScheduleDTO;
import xyz.parkh.doing.domain.model.schedule.ScheduleType;
import xyz.parkh.doing.repository.ScheduleRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 일정 생성
    public void addScheduleForScheduleDTO(ScheduleDTO scheduleDTO) {
        ScheduleType scheduleType = scheduleDTO.getScheduleType();
        if (scheduleType == null) {
            throw new NullPointerException();
        }
        Schedule schedule = null;

        if (scheduleType == ScheduleType.HABIT) {
            schedule = scheduleDTO.convertHabitSchedule();
        } else if (scheduleType == ScheduleType.TODO) {
            schedule = scheduleDTO.convertToDoSchedule();
        }
        scheduleRepository.save(schedule);
    }

    // 일정 목록 조회

    // 일정 상세 조회

    // 일정 수정

    // 일정 삭제


}
