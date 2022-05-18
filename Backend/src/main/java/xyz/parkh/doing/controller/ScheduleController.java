package xyz.parkh.doing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.parkh.doing.domain.Schedule;
import xyz.parkh.doing.service.ScheduleService;


@RestController
@RequestMapping(("/schedule"))
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    //    일정 목록 조회
//    localhost/api/schedule/parkh?isPublic=true&endTime=2022-05-18T06:34:20
//    파라미터를
//    isPublic, endTime // 빡빡하게 잡아야하나?
//    Schedule 널널하게 잡아야하나?
    @GetMapping("/{userId}")
//    public ResponseEntity<List<Schedule>> getScheduleList(@PathVariable("userId") String userId, Schedule schedule) {
    public ResponseEntity<Schedule> getScheduleList(@PathVariable("userId") String userId, boolean isPublic) {
        System.out.println("userId = " + userId);
//        System.out.println("schedule = " + schedule);

//        return ResponseEntity.ok().body(schedule);
        return ResponseEntity.ok().body(null);
    }

    // 일정 상세 정보 조회
    @GetMapping("/{userId}/{scheduleNo}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable("userId") String userId, @PathVariable("scheduleNo") Integer scheduleNo) {
        System.out.println("userId = " + userId);
        System.out.println("scheduleNo = " + scheduleNo);
//        Schedule schedule = scheduleService.getScheduleByNo(scheduleNo);
//        return ResponseEntity.ok().body(schedule);
        return ResponseEntity.ok().body(null);

    }

}
