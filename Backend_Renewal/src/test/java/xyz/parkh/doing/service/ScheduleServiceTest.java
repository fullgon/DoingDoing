package xyz.parkh.doing.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.friend.model.FriendInfo;
import xyz.parkh.doing.domain.friend.model.FriendshipState;
import xyz.parkh.doing.domain.friend.service.FriendService;
import xyz.parkh.doing.domain.schedule.model.AllCategorizedScheduleList;
import xyz.parkh.doing.domain.schedule.model.OpenScope;
import xyz.parkh.doing.domain.schedule.model.Period;
import xyz.parkh.doing.domain.schedule.model.ScheduleDto;
import xyz.parkh.doing.domain.schedule.repository.ScheduleRepository;
import xyz.parkh.doing.domain.schedule.service.ScheduleService;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.user.repository.UserRepository;
import xyz.parkh.doing.domain.user.service.UserService;

import java.time.LocalDate;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ScheduleServiceTest {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendService friendService;

    @Test
    public void 할일_월간_일정_추가() {
        User user = userService.findByAuthId("authIdA");
        Period period = Period.createMonthlyPeriod(2022, 8);

        ScheduleDto scheduleDTO = ScheduleDto.createForToDoSchedule(user, "title", OpenScope.PUBLIC, period, true);

        scheduleService.addSchedule(scheduleDTO);
    }

    @Test
    public void 습관_월간_일정_추가() {
        User user = userService.findByAuthId("authIdA");
        Period period = Period.createMonthlyPeriod(2022, 8);
        ScheduleDto scheduleDTO = ScheduleDto.createForToDoSchedule(user, "title", OpenScope.PUBLIC, period, true);

        scheduleService.addSchedule(scheduleDTO);
    }

    @Test
    public void 오늘_일정_전체_조회_월간() {
        AllCategorizedScheduleList publicSchedule1 = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), "authIdA");
        Assertions.assertEquals(2, publicSchedule1.getMonthly().getToDoScheduleList().size());
        Assertions.assertEquals(1, publicSchedule1.getMonthly().getHabitScheduleList().size());

        AllCategorizedScheduleList publicSchedule2 = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), "authIdA", "authIdC");
        Assertions.assertEquals(2, publicSchedule2.getMonthly().getToDoScheduleList().size());
        Assertions.assertEquals(1, publicSchedule2.getMonthly().getHabitScheduleList().size());

        AllCategorizedScheduleList privateSchedule = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), "authIdA", "authIdA");
        Assertions.assertEquals(6, privateSchedule.getMonthly().getToDoScheduleList().size());
        Assertions.assertEquals(3, privateSchedule.getMonthly().getHabitScheduleList().size());

        AllCategorizedScheduleList friendSchedule = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), "authIdA", "authIdB");
        Assertions.assertEquals(4, friendSchedule.getMonthly().getToDoScheduleList().size());
        Assertions.assertEquals(2, friendSchedule.getMonthly().getHabitScheduleList().size());
    }
    @Test
    public void 오늘_일정_전체_조회_주간() {
        AllCategorizedScheduleList publicSchedule1 = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), "authIdA");
        Assertions.assertEquals(2, publicSchedule1.getWeekly().getToDoScheduleList().size());
        Assertions.assertEquals(1, publicSchedule1.getWeekly().getHabitScheduleList().size());

        AllCategorizedScheduleList publicSchedule2 = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), "authIdA", "authIdC");
        Assertions.assertEquals(2, publicSchedule2.getWeekly().getToDoScheduleList().size());
        Assertions.assertEquals(1, publicSchedule2.getWeekly().getHabitScheduleList().size());

        AllCategorizedScheduleList privateSchedule = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), "authIdA", "authIdA");
        Assertions.assertEquals(6, privateSchedule.getWeekly().getToDoScheduleList().size());
        Assertions.assertEquals(3, privateSchedule.getWeekly().getHabitScheduleList().size());

        AllCategorizedScheduleList friendSchedule = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), "authIdA", "authIdB");
        Assertions.assertEquals(4, friendSchedule.getWeekly().getToDoScheduleList().size());
        Assertions.assertEquals(2, friendSchedule.getWeekly().getHabitScheduleList().size());
    }
    @Test
    public void 오늘_일정_전체_조회_일간() {
        AllCategorizedScheduleList publicSchedule1 = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), "authIdA");
        Assertions.assertEquals(2, publicSchedule1.getDaily().getToDoScheduleList().size());
        Assertions.assertEquals(1, publicSchedule1.getDaily().getHabitScheduleList().size());

        AllCategorizedScheduleList publicSchedule2 = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), "authIdA", "authIdC");
        Assertions.assertEquals(2, publicSchedule2.getDaily().getToDoScheduleList().size());
        Assertions.assertEquals(1, publicSchedule2.getDaily().getHabitScheduleList().size());

        AllCategorizedScheduleList privateSchedule = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), "authIdA", "authIdA");
        Assertions.assertEquals(6, privateSchedule.getDaily().getToDoScheduleList().size());
        Assertions.assertEquals(3, privateSchedule.getDaily().getHabitScheduleList().size());

        AllCategorizedScheduleList friendSchedule = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), "authIdA", "authIdB");
        Assertions.assertEquals(4, friendSchedule.getDaily().getToDoScheduleList().size());
        Assertions.assertEquals(2, friendSchedule.getDaily().getHabitScheduleList().size());
    }

}