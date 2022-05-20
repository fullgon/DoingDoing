package xyz.parkh.doing.service.schedule;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.parkh.doing.domain.ScheduleVo;
import xyz.parkh.doing.mapper.ScheduleMapper;

import java.util.List;

@Log4j
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleMapper scheduleMapper;

    @Override
    public List<ScheduleVo> getScheduleList(String userId) {
        return null;
    }

    @Override
    public ScheduleVo getScheduleByNo(Integer no) {
        return null;
    }
}
