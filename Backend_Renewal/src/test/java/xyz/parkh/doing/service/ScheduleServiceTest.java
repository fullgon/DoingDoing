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

    @Before
    public void 초기_데이터() throws Exception {
        User userA = User.builder().authId("userAId").name("userA").build();
        User userB = User.builder().authId("userBId").name("userB").build();
        User aFriend = User.builder().authId("AFriendId").name("AFriendId").build();

        userRepository.save(userA);
        userRepository.save(userB);
        userRepository.save(aFriend);

        friendService.requestFriendApplication(userA.getAuthId(), aFriend.getAuthId());
        FriendInfo friendApplication = friendService.getReceiveFriendApplication(userA.getAuthId(), aFriend.getAuthId(), FriendshipState.REQUEST);
        friendService.responseAccept(friendApplication.getId());

        LocalDate now = LocalDate.now();
        Period monthlyPeriod = Period.createMonthlyPeriod(now);
        Period weeklyPeriod = Period.createWeeklyPeriod(now);
        Period dailyPeriod = Period.createDailyPeriod(now);

        ScheduleDto monthToDoPublicTrue = ScheduleDto.createForToDoSchedule(userA, "MonthToDoPublicTrue", OpenScope.PUBIC, monthlyPeriod, true);
        ScheduleDto monthToDoPublicFalse = ScheduleDto.createForToDoSchedule(userA, "MonthToDoPublicFalse", OpenScope.PUBIC, monthlyPeriod, false);
        ScheduleDto monthHabitPublic = ScheduleDto.createForHabitSchedule(userA, "MonthHabitPublic", OpenScope.PUBIC, monthlyPeriod);
        ScheduleDto monthToDoPrivateTrue = ScheduleDto.createForToDoSchedule(userA, "MonthToDoPrivateTrue", OpenScope.PRIVATE, monthlyPeriod, true);
        ScheduleDto monthToDoPrivateFalse = ScheduleDto.createForToDoSchedule(userA, "MonthToDoPublicTrue", OpenScope.PRIVATE, monthlyPeriod, false);
        ScheduleDto monthHabitPrivate = ScheduleDto.createForHabitSchedule(userA, "MonthHabitPrivate", OpenScope.PRIVATE, monthlyPeriod);
        ScheduleDto monthToDoFriendTrue = ScheduleDto.createForToDoSchedule(userA, "MonthToDoFriendTrue", OpenScope.FRIEND, monthlyPeriod, true);
        ScheduleDto monthToDoFriendFalse = ScheduleDto.createForToDoSchedule(userA, "MonthToDoFriendTrue", OpenScope.FRIEND, monthlyPeriod, false);
        ScheduleDto monthHabitFriend = ScheduleDto.createForHabitSchedule(userA, "MonthHabitFriend", OpenScope.FRIEND, monthlyPeriod);

        scheduleService.addSchedule(monthToDoPublicTrue);
        scheduleService.addSchedule(monthToDoPublicFalse);
        scheduleService.addSchedule(monthHabitPublic);
        scheduleService.addSchedule(monthToDoPrivateFalse);
        scheduleService.addSchedule(monthToDoPrivateTrue);
        scheduleService.addSchedule(monthHabitPrivate);
        scheduleService.addSchedule(monthToDoFriendFalse);
        scheduleService.addSchedule(monthToDoFriendTrue);
        scheduleService.addSchedule(monthHabitFriend);

        ScheduleDto weekToDoPublicTrue = ScheduleDto.createForToDoSchedule(userA, "weekToDoPublicTrue", OpenScope.PUBIC, weeklyPeriod, true);
        ScheduleDto weekToDoPublicFalse = ScheduleDto.createForToDoSchedule(userA, "weekToDoPublicFalse", OpenScope.PUBIC, weeklyPeriod, false);
        ScheduleDto weekHabitPublic = ScheduleDto.createForHabitSchedule(userA, "weekHabitPublic", OpenScope.PUBIC, weeklyPeriod);
        ScheduleDto weekToDoPrivateTrue = ScheduleDto.createForToDoSchedule(userA, "weekToDoPrivateTrue", OpenScope.PRIVATE, weeklyPeriod, true);
        ScheduleDto weekToDoPrivateFalse = ScheduleDto.createForToDoSchedule(userA, "weekToDoPublicTrue", OpenScope.PRIVATE, weeklyPeriod, false);
        ScheduleDto weekHabitPrivate = ScheduleDto.createForHabitSchedule(userA, "weekHabitPrivate", OpenScope.PRIVATE, weeklyPeriod);

        ScheduleDto weekToDoFriendTrue = ScheduleDto.createForToDoSchedule(userA, "weekToDoFriendTrue", OpenScope.FRIEND, weeklyPeriod, true);
        ScheduleDto weekToDoFriendFalse = ScheduleDto.createForToDoSchedule(userA, "weekToDoFriendTrue", OpenScope.FRIEND, weeklyPeriod, false);
        ScheduleDto weekHabitFriend = ScheduleDto.createForHabitSchedule(userA, "weekHabitFriend", OpenScope.FRIEND, weeklyPeriod);
        scheduleService.addSchedule(weekToDoPublicTrue);
        scheduleService.addSchedule(weekToDoPublicFalse);
        scheduleService.addSchedule(weekHabitPublic);
        scheduleService.addSchedule(weekToDoPrivateTrue);
        scheduleService.addSchedule(weekToDoPrivateFalse);
        scheduleService.addSchedule(weekHabitPrivate);
        scheduleService.addSchedule(weekToDoFriendTrue);
        scheduleService.addSchedule(weekToDoFriendFalse);
        scheduleService.addSchedule(weekHabitFriend);

        ScheduleDto dayToDoPublicTrue = ScheduleDto.createForToDoSchedule(userA, "dayToDoPublicTrue", OpenScope.PUBIC, dailyPeriod, true);
        ScheduleDto dayToDoPublicFalse = ScheduleDto.createForToDoSchedule(userA, "dayToDoPublicFalse", OpenScope.PUBIC, dailyPeriod, false);
        ScheduleDto dayHabitPublic = ScheduleDto.createForHabitSchedule(userA, "dayHabitPublic", OpenScope.PUBIC, dailyPeriod);
        ScheduleDto dayToDoPrivateTrue = ScheduleDto.createForToDoSchedule(userA, "dayToDoPrivateTrue", OpenScope.PRIVATE, dailyPeriod, true);
        ScheduleDto dayToDoPrivateFalse = ScheduleDto.createForToDoSchedule(userA, "dayToDoPublicTrue", OpenScope.PRIVATE, dailyPeriod, false);
        ScheduleDto dayHabitPrivate = ScheduleDto.createForHabitSchedule(userA, "dayHabitPrivate", OpenScope.PRIVATE, dailyPeriod);
        ScheduleDto dayToDoFriendTrue = ScheduleDto.createForToDoSchedule(userA, "dayToDoFriendTrue", OpenScope.FRIEND, dailyPeriod, true);
        ScheduleDto dayToDoFriendFalse = ScheduleDto.createForToDoSchedule(userA, "dayToDoFriendTrue", OpenScope.FRIEND, dailyPeriod, false);
        ScheduleDto dayHabitFriend = ScheduleDto.createForHabitSchedule(userA, "dayHabitFriend", OpenScope.FRIEND, dailyPeriod);

        scheduleService.addSchedule(dayToDoPublicTrue);
        scheduleService.addSchedule(dayToDoPublicFalse);
        scheduleService.addSchedule(dayHabitPublic);
        scheduleService.addSchedule(dayToDoPrivateTrue);
        scheduleService.addSchedule(dayToDoPrivateFalse);
        scheduleService.addSchedule(dayHabitPrivate);
        scheduleService.addSchedule(dayToDoFriendTrue);
        scheduleService.addSchedule(dayToDoFriendFalse);
        scheduleService.addSchedule(dayHabitFriend);
    }

    @Test
    public void 할일_월간_일정_추가() {
        User user = User.builder().name("name").build();
        userRepository.save(user);
        Period period = Period.createMonthlyPeriod(2022, 8);

        ScheduleDto scheduleDTO = ScheduleDto.createForToDoSchedule(user, "title", OpenScope.PUBIC, period, true);

        scheduleService.addSchedule(scheduleDTO);
    }

    @Test
    public void 습관_월간_일정_추가() {
        User user = userService.findByAuthId("userAId");
        Period period = Period.createMonthlyPeriod(2022, 8);
        ScheduleDto scheduleDTO = ScheduleDto.createForToDoSchedule(user, "title", OpenScope.PUBIC, period, true);

        scheduleService.addSchedule(scheduleDTO);
    }

    @Test
    public void 오늘_일정_전체_조회_월간() {
        AllCategorizedScheduleList publicSchedule1 = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), "userAId");
        Assertions.assertEquals(2, publicSchedule1.getMonthly().getToDoScheduleList().size());
        Assertions.assertEquals(1, publicSchedule1.getMonthly().getHabitScheduleList().size());

        AllCategorizedScheduleList publicSchedule2 = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), "userAId", "userBId");
        Assertions.assertEquals(2, publicSchedule2.getMonthly().getToDoScheduleList().size());
        Assertions.assertEquals(1, publicSchedule2.getMonthly().getHabitScheduleList().size());

        AllCategorizedScheduleList privateSchedule = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), "userAId", "userAId");
        Assertions.assertEquals(6, privateSchedule.getMonthly().getToDoScheduleList().size());
        Assertions.assertEquals(3, privateSchedule.getMonthly().getHabitScheduleList().size());

        AllCategorizedScheduleList friendSchedule = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), "userAId", "AFriendId");
        Assertions.assertEquals(4, friendSchedule.getMonthly().getToDoScheduleList().size());
        Assertions.assertEquals(2, friendSchedule.getMonthly().getHabitScheduleList().size());
    }
    @Test
    public void 오늘_일정_전체_조회_주간() {
        AllCategorizedScheduleList publicSchedule1 = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), "userAId");
        Assertions.assertEquals(2, publicSchedule1.getWeekly().getToDoScheduleList().size());
        Assertions.assertEquals(1, publicSchedule1.getWeekly().getHabitScheduleList().size());

        AllCategorizedScheduleList publicSchedule2 = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), "userAId", "userBId");
        Assertions.assertEquals(2, publicSchedule2.getWeekly().getToDoScheduleList().size());
        Assertions.assertEquals(1, publicSchedule2.getWeekly().getHabitScheduleList().size());

        AllCategorizedScheduleList privateSchedule = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), "userAId", "userAId");
        Assertions.assertEquals(6, privateSchedule.getWeekly().getToDoScheduleList().size());
        Assertions.assertEquals(3, privateSchedule.getWeekly().getHabitScheduleList().size());

        AllCategorizedScheduleList friendSchedule = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), "userAId", "AFriendId");
        Assertions.assertEquals(4, friendSchedule.getWeekly().getToDoScheduleList().size());
        Assertions.assertEquals(2, friendSchedule.getWeekly().getHabitScheduleList().size());
    }
    @Test
    public void 오늘_일정_전체_조회_일간() {
        AllCategorizedScheduleList publicSchedule1 = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), "userAId");
        Assertions.assertEquals(2, publicSchedule1.getDaily().getToDoScheduleList().size());
        Assertions.assertEquals(1, publicSchedule1.getDaily().getHabitScheduleList().size());

        AllCategorizedScheduleList publicSchedule2 = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), "userAId", "userBId");
        Assertions.assertEquals(2, publicSchedule2.getDaily().getToDoScheduleList().size());
        Assertions.assertEquals(1, publicSchedule2.getDaily().getHabitScheduleList().size());

        AllCategorizedScheduleList privateSchedule = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), "userAId", "userAId");
        Assertions.assertEquals(6, privateSchedule.getDaily().getToDoScheduleList().size());
        Assertions.assertEquals(3, privateSchedule.getDaily().getHabitScheduleList().size());

        AllCategorizedScheduleList friendSchedule = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), "userAId", "AFriendId");
        Assertions.assertEquals(4, friendSchedule.getDaily().getToDoScheduleList().size());
        Assertions.assertEquals(2, friendSchedule.getDaily().getHabitScheduleList().size());
    }

}