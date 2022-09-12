package xyz.parkh.doing.api.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.parkh.doing.api.model.request.AddScheduleRequest;
import xyz.parkh.doing.api.model.response.Check;
import xyz.parkh.doing.domain.schedule.model.*;
import xyz.parkh.doing.domain.schedule.service.ScheduleService;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.user.service.UserService;
import xyz.parkh.doing.security.TokenProvider;

import java.time.LocalDate;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final UserService userService;
    private final TokenProvider tokenProvider;

    // 일정 추가
    @PostMapping("/{authId}")
    public ResponseEntity addSchedule(
            @PathVariable String authId,
            @RequestBody AddScheduleRequest addScheduleRequest,
            @RequestHeader("Authorization") String bearerToken) {
        String requesterId = tokenProvider.getUserIdAtBearerToken(bearerToken);
        User user = userService.findByAuthId(authId);
        ScheduleAddDto scheduleAddDto = addScheduleRequest.convert(user);
        scheduleService.addSchedule(requesterId, scheduleAddDto);

        return ResponseEntity.noContent().build();
    }

    // 날짜 기준 모든 일정 조회
    @GetMapping("/{authId}/all/{localDate}")
    public ResponseEntity<AllCategorizedScheduleList> getAllScheduleByLocalDate(
            @PathVariable String authId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate,
            @RequestHeader("Authorization") String bearerToken) {
        String targetId = authId;
        String requesterId = tokenProvider.getUserIdAtBearerToken(bearerToken);

        AllCategorizedScheduleList allCategorizedScheduleList = scheduleService.findAllCategorizedScheduleList(localDate, targetId, requesterId);
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
                                         @RequestBody UpdateScheduleRequest updateScheduleRequest,
                                         String userInJwt) {
        ScheduleChangeDto scheduleChangeDto = ScheduleChangeDto.builder()
                .title(updateScheduleRequest.getTitle())
                .openScope(updateScheduleRequest.getOpenScope())
                .period(updateScheduleRequest.getPeriod())
                .isCompleted(updateScheduleRequest.getIsCompleted())
                .scheduleType(updateScheduleRequest.getScheduleType())
                .build();

        scheduleService.updateSchedule(scheduleId, scheduleChangeDto);
        return ResponseEntity.noContent().build();
    }

    @Getter
    static class UpdateScheduleRequest {
        private String title;
        private OpenScope openScope;
        private Period period;
        private Boolean isCompleted;
        private ScheduleType scheduleType;
    }

    // 일정 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity removeSchedule(@PathVariable Long scheduleId,
                                         String userInJwt
    ) {
        String targetId = scheduleService.findByScheduleId(scheduleId).getUser().getAuthId();
        String requesterId = userInJwt;
        scheduleService.deleteSchedule(scheduleId, targetId, requesterId);
        return ResponseEntity.noContent().build();
    }


}
