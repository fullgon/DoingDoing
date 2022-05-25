package xyz.parkh.doing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.ScheduleStatusVo;
import xyz.parkh.doing.domain.ScheduleVo;
import xyz.parkh.doing.mapper.ScheduleMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j
@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleMapper scheduleMapper;

    public List<ScheduleVo> getScheduleList(String userId) {
        return null;
    }

    public Map<String, Object> getScheduleByNo(String userIdInJwt, String userId, Integer scheduleNo) {

        HashMap<String, Object> jsonData = new HashMap<>();
        ScheduleVo schedule = scheduleMapper.selectByNo(scheduleNo);
        jsonData.put("schedule", schedule);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", scheduleNo);
        jsonData.put("result", result);
        return jsonData;
    }

    public Map<String, Object> readScheduleList(String userIdInJwt, String userId, ScheduleStatusVo scheduleStatusVo) {
        HashMap<String, Object> jsonData = new HashMap<>();
        List<ScheduleVo> scheduleList = scheduleMapper.selectByUserId(userId);
        jsonData.put("scheduleList", scheduleList);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", scheduleStatusVo);
        jsonData.put("result", result);

        return jsonData;
    }

    public Map<String, Object> createSchedule(String userIdInJwt, ScheduleVo scheduleVo) {

        HashMap<String, Object> jsonData = new HashMap<>();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", scheduleVo);
        jsonData.put("result", result);
        return jsonData;
    }

    public Map<String, Object> updateSchedule(String userIdInJwt, String userId, ScheduleVo scheduleVo) {
        HashMap<String, Object> jsonData = new HashMap<>();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", scheduleVo);
        jsonData.put("result", result);
        return jsonData;
    }

    public Map<String, Object> removeSchedule(String userIdInJwt, Integer scheduleNo) {
        HashMap<String, Object> jsonData = new HashMap<>();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", "message");
        jsonData.put("result", userIdInJwt);
        return jsonData;
    }
}
