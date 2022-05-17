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

    @GetMapping("/{scheduleId}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable("scheduleId") Integer scheduleId) {
        System.out.println("scheduleId = " + scheduleId);
        Schedule schedule = scheduleService.getScheduleByNo(scheduleId);
        System.out.println("schedule = " + schedule);
        System.out.println("박현준");
        return ResponseEntity.ok().body(schedule);
    }

}
