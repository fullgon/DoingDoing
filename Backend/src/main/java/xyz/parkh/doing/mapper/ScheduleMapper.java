package xyz.parkh.doing.mapper;

import xyz.parkh.doing.domain.ScheduleVo;

import java.util.List;

public interface ScheduleMapper {

    int insertSchedule(ScheduleVo scheduleVo);

    ScheduleVo selectScheduleByNo(Integer no);

    List<ScheduleVo> selectScheduleList(String userId);

    int updateSchedule(ScheduleVo scheduleVo);

    int deleteScheduleByNo(Integer no);


}
