package xyz.parkh.doing.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.parkh.doing.api.model.request.AddScheduleRequest;
import xyz.parkh.doing.api.model.request.GetScheduleRequest;
import xyz.parkh.doing.api.model.response.Check;
import xyz.parkh.doing.domain.schedule.model.AllCategorizedScheduleList;
import xyz.parkh.doing.domain.schedule.model.ScheduleDto;
import xyz.parkh.doing.domain.schedule.service.ScheduleService;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.user.service.UserService;

import java.time.LocalDate;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final UserService userService;

    // 일정 추가
    @PostMapping("/{authId}")
    public ResponseEntity addSchedule(
            @PathVariable String authId,
            @RequestBody AddScheduleRequest addScheduleRequest,
            String userInJwt) {
        User user = userService.findByAuthId(authId);
        ScheduleDto scheduleDto = addScheduleRequest.convert(user);
        scheduleService.addSchedule(scheduleDto);

        return ResponseEntity.noContent().build();
    }

    // 날짜 기준 모든 일정 조회
    @GetMapping("/{authId}/all/{localDate}")
    public ResponseEntity<AllCategorizedScheduleList> getAllScheduleByLocalDate(
            @PathVariable String authId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate,
            String userInJwt) {
        String targetId = authId;
        String requestId = userInJwt;
        AllCategorizedScheduleList allCategorizedScheduleList = scheduleService.findAllCategorizedScheduleList(localDate, targetId, requestId);

        return ResponseEntity.ok(allCategorizedScheduleList);
    }

    // 기간 타입별 일정 조회
    @GetMapping("/{authId}")
    public ResponseEntity getScheduleByPeriod(@PathVariable String authId,
                                              String userInJwt) {

        return ResponseEntity.noContent().build();
    }

    // 일정이 존재 하는 날짜 조회
    @GetMapping("/exist/{authId}")
    public ResponseEntity<Check> getExistScheduleLocalDate(@PathVariable String authId,
                                                           String userInJwt) {

        return ResponseEntity.noContent().build();
    }

    // 일정 수정
    @PatchMapping("/{scheduleId}")
    public ResponseEntity updateSchedule(@PathVariable Long scheduleId,
                                         String userInJwt) {

        return ResponseEntity.noContent().build();
    }

    // 일정 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity removeSchedule(@PathVariable Long scheduleId,
                                         String userInJwt) {

        return ResponseEntity.noContent().build();
    }
}
