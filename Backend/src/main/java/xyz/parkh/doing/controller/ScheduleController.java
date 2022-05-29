package xyz.parkh.doing.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import xyz.parkh.doing.domain.model.ScheduleStatusDto;
import xyz.parkh.doing.domain.entity.ScheduleVo;
import xyz.parkh.doing.service.ScheduleService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(("/api/schedules"))
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // jwt, isComplete, isTimeOut
    // 일정 목록 조회
    @GetMapping("/{userId}")
    public Map<String, Object> getByUserId(@PathVariable("userId") String userId,
                                           @RequestBody ScheduleStatusDto ScheduleStatusDto,
                                           HttpServletRequest request) {
        String userIdInJwt = (String) request.getAttribute("userId");

        return scheduleService.readScheduleList(userIdInJwt, userId, ScheduleStatusDto);
    }

    // jwt
    // 일정 정보 조회
    @GetMapping("/{userId}/{scheduleNo}")
    public Map<String, Object> getByUserIdByScheduleNo(@PathVariable("userId") String userId,
                                                       @PathVariable("scheduleNo") Integer scheduleNo,
                                                       HttpServletRequest request) {
        String userIdInJwt = (String) request.getAttribute("userId");

        return scheduleService.getScheduleByNo(userIdInJwt, userId, scheduleNo);

    }

    // jwt, title, content, isPublic, endTime
    // 일정 정보 등록
    @PostMapping("/{userId}")
    public Map<String, Object> postByUserIdByScheduleNo(@PathVariable("userId") String userId,
                                                        @RequestBody ScheduleVo scheduleVo,
                                                        HttpServletRequest request) {
        String userIdInJwt = (String) request.getAttribute("userId");

        return scheduleService.createSchedule(userIdInJwt, scheduleVo);
    }

    // jwt, title, content, isPublic, endTime
    // 일정 정보 수정
    @PutMapping("/{userId}/{scheduleNo}")
    public Map<String, Object> putByUserIdByScheduleNo(@PathVariable("userId") String userId,
                                                       @PathVariable("scheduleNo") Integer scheduleNo,
                                                       @RequestBody ScheduleVo scheduleVo,
                                                       HttpServletRequest request) {
        String userIdInJwt = (String) request.getAttribute("userId");

        return scheduleService.updateSchedule(userIdInJwt, userId, scheduleVo);
    }

    // 일정 정보 삭제
    @DeleteMapping("/{userId}/{scheduleNo}")
    public Map<String, Object> deleteByUserIdByScheduleNo(@PathVariable("userId") String userId,
                                                          @PathVariable("scheduleNo") Integer scheduleNo,
                                                          HttpServletRequest request) {
        String userIdInJwt = (String) request.getAttribute("userId");

        return scheduleService.removeSchedule(userIdInJwt, scheduleNo);

    }
}
