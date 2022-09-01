package xyz.parkh.doing.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.schedule.entity.Schedule;
import xyz.parkh.doing.domain.schedule.model.*;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.schedule.repository.ScheduleRepository;
import xyz.parkh.doing.domain.user.repository.UserRepository;
import xyz.parkh.doing.domain.schedule.service.ScheduleService;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ScheduleServiceTest {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    @Rollback(value = false)
    public void 할일_월간_일정_추가() {
        User user = User.builder().name("name").build();
        userRepository.save(user);
        Period period = Period.createTodayMonthlyPeriod();

        ScheduleDto scheduleDTO = ScheduleDto.builder()
                .user(user).title("title")
                .openScope(OpenScope.PUBIC).period(period).scheduleType(ScheduleType.TODO)
                .isCompleted(true).build();

        scheduleService.addSchedule(scheduleDTO);
    }

    @Test
    @Rollback(value = false)
    public void 습관_월간_일정_추가() {
        User user = User.builder().name("name").build();
        userRepository.save(user);
        Period period = Period.createTodayMonthlyPeriod();

        ScheduleDto scheduleDTO = ScheduleDto.builder()
                .user(user).title("title")
                .openScope(OpenScope.PUBIC).period(period)
                .scheduleType(ScheduleType.HABIT).build();

        scheduleService.addSchedule(scheduleDTO);
    }

    @Test
    public void 습관_월간_일정_조회() {
        User user = User.builder().authId("userA").name("name").build();
        userRepository.save(user);
        Period monthlyPeriod = Period.createTodayMonthlyPeriod();
        Period dailyPeriod = Period.createTodayDailyPeriod();

        ScheduleDto scheduleDTO1 = ScheduleDto.builder()
                .user(user).title("title")
                .openScope(OpenScope.PUBIC).period(monthlyPeriod)
                .scheduleType(ScheduleType.HABIT).build();

        scheduleService.addSchedule(scheduleDTO1);

        // TODO, HABIT 구분 못 함
//        ScheduleDto scheduleDTO2 = ScheduleDto.builder()
//                .user(user).title("title")
//                .openScope(OpenScope.PUBIC).period(monthlyPeriod)
//                .scheduleType(ScheduleType.TODO).build();

//        scheduleService.addSchedule(scheduleDTO2);

        ScheduleDto scheduleDTO3 = ScheduleDto.builder()
                .user(user).title("title")
                .openScope(OpenScope.PRIVATE).period(monthlyPeriod)
                .scheduleType(ScheduleType.HABIT).build();

        scheduleService.addSchedule(scheduleDTO3);

        ScheduleDto scheduleDTO4 = ScheduleDto.builder()
                .user(user).title("title")
                .openScope(OpenScope.PUBIC).period(dailyPeriod)
                .scheduleType(ScheduleType.HABIT).build();

        scheduleService.addSchedule(scheduleDTO4);

        ScheduleConditionDTO scheduleConditionDTO = new ScheduleConditionDTO("userA", monthlyPeriod, ScheduleType.HABIT, OpenScope.PUBIC);
        List<Schedule> scheduleList = scheduleService.findScheduleList(scheduleConditionDTO);

        Assertions.assertEquals(scheduleList.size(), 1);
    }

}