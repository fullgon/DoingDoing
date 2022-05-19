package xyz.parkh.doing.service.schedule;

import xyz.parkh.doing.domain.ScheduleVo;

import java.util.List;

public interface ScheduleService {
    ScheduleVo getScheduleByNo(Integer no);

    List<ScheduleVo> getScheduleList(String userId);
}
