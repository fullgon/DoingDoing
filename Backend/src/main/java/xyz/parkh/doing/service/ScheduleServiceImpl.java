package xyz.parkh.doing.service;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.parkh.doing.domain.Schedule;
import xyz.parkh.doing.mapper.ScheduleMapper;

@Log4j
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleMapper scheduleMapper;

    @Override
    public Schedule getScheduleByNo(Integer no) {
        log.info(no);
        log.info(scheduleMapper);
        Schedule schedule = scheduleMapper.selectScheduleByNo(no);

        log.info(schedule);
        return schedule;
    }
}
