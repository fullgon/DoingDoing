package xyz.parkh.doing.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.parkh.doing.api.model.request.AddScheduleRequest;
import xyz.parkh.doing.api.model.request.GetScheduleRequest;
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

    // TODO 인증 추가 전까지 요청에 요청한 사용자 아이디 받음
    @GetMapping("/{authId}/{localDate}")
    public ResponseEntity<AllCategorizedScheduleList> getAllScheduleDate(
            @PathVariable String authId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate,
            String userInJwt) {
        String targetId = authId;
        String requestId = userInJwt;
        AllCategorizedScheduleList allCategorizedScheduleList = scheduleService.findAllCategorizedScheduleList(localDate, targetId, requestId);

        return ResponseEntity.ok(allCategorizedScheduleList);
    }

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


}
