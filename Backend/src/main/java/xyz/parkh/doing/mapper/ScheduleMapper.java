package xyz.parkh.doing.mapper;

import xyz.parkh.doing.domain.ScheduleVo;

import java.util.List;

public interface ScheduleMapper {
    int insert(ScheduleVo ScheduleVo);

    ScheduleVo selectByScheduleNo(Integer ScheduleNo);

    List<ScheduleVo> selectAll();

    int update(ScheduleVo ScheduleVo);

    int delete(Integer ScheduleNo);
}
