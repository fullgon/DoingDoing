package xyz.parkh.doing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.parkh.doing.domain.ScheduleStatusVo;
import xyz.parkh.doing.domain.ScheduleVo;
import xyz.parkh.doing.service.schedule.ScheduleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(("/schedule"))
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    // 일정 목록 조회
    @GetMapping("/{userId}")
    public Map<String, Object> getByUserId(@PathVariable("userId") String userId, ScheduleStatusVo ScheduleStatusVo) {
        HashMap<String, Object> jsonData = new HashMap<>();
        List<ScheduleVo> scheduleList = scheduleService.getScheduleList(userId);
        jsonData.put("scheduleList", scheduleList);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", "message");
        jsonData.put("result", result);

        return jsonData;
    }

    // 일정 정보 조회
    @GetMapping("/{userId}/{scheduleNo}")
    public Map<String, Object> getByUserIdByScheduleNo(@PathVariable("userId") String userId, @PathVariable("scheduleNo") Integer scheduleNo) {
        HashMap<String, Object> jsonData = new HashMap<>();
        ScheduleVo schedule = scheduleService.getScheduleByNo(scheduleNo);
        jsonData.put("schedule", schedule);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", "message");
        jsonData.put("result", result);
        return jsonData;
    }

    // 일정 정보 등록
    @PostMapping("/{userId}/{scheduleNo}")
    public Map<String, Object> postByUserIdByScheduleNo(@PathVariable("userId") String userId, @PathVariable("scheduleNo") Integer scheduleNo, ScheduleVo scheduleVo) {
        HashMap<String, Object> jsonData = new HashMap<>();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", "message");
        jsonData.put("result", result);
        return jsonData;
    }

    // 일정 정보 수정
    @PatchMapping("/{userId}/{scheduleNo}")
    public Map<String, Object> patchByUserIdByScheduleNo(@PathVariable("userId") String userId, @PathVariable("scheduleNo") Integer scheduleNo, ScheduleVo scheduleVo) {
        HashMap<String, Object> jsonData = new HashMap<>();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", "message");
        jsonData.put("result", result);
        return jsonData;
    }

    // 일정 정보 삭제
    @DeleteMapping("/{userId}/{scheduleNo}")
    public Map<String, Object> deleteByUserIdByScheduleNo(@PathVariable("userId") String userId, @PathVariable("scheduleNo") Integer scheduleNo) {
        HashMap<String, Object> jsonData = new HashMap<>();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", "message");
        jsonData.put("result", result);
        return jsonData;
    }
}
