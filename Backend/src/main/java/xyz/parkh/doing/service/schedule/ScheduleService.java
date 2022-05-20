package xyz.parkh.doing.service.schedule;

import xyz.parkh.doing.domain.ScheduleVo;

import java.util.List;

public interface ScheduleService {
    List<ScheduleVo> getScheduleList(String userId);

    ScheduleVo getScheduleByNo(Integer no);
}
