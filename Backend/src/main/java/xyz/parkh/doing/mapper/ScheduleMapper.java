package xyz.parkh.doing.mapper;

import xyz.parkh.doing.domain.ScheduleVo;

import java.util.List;

public interface ScheduleMapper {

    ScheduleVo selectScheduleByNo(Integer no);

    List<ScheduleVo> selectScheduleList(String userId);
}
