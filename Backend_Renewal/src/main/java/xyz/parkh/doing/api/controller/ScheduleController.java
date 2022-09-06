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

import java.time.LocalDate;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

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

        System.out.println("authId = " + authId);
        System.out.println("userInJwt = " + userInJwt);
        System.out.println("addScheduleRequest = " + addScheduleRequest);

        return ResponseEntity.noContent().build();
    }


}
