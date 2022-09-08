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
import xyz.parkh.doing.domain.schedule.entity.Schedule;
import xyz.parkh.doing.domain.schedule.entity.ToDoSchedule;
import xyz.parkh.doing.domain.schedule.model.*;
import xyz.parkh.doing.domain.schedule.repository.ScheduleRepository;
import xyz.parkh.doing.domain.schedule.service.ScheduleService;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.user.model.UserDetailInfo;
import xyz.parkh.doing.domain.user.repository.UserRepository;
import xyz.parkh.doing.domain.user.service.UserService;

import java.time.LocalDate;
import java.util.Optional;

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

    User userA;
    User userB;
    User userC;
    Period monthlyPeriod;
    Period weeklyPeriod;
    Period dailyPeriod;


    @Before
    public void init(){
        if (userService.findByAuthId("authIdA") == null) {
            UserDetailInfo userDetailInfoA = new UserDetailInfo("authIdA", "passwordA", "nameA", "emailA@naver.com", "companyA");
            userA = userService.signUp(userDetailInfoA);
        }
        if (userService.findByAuthId("authIdB") == null) {
            UserDetailInfo userDetailInfoB = new UserDetailInfo("authIdB", "passwordB", "nameB", "emailB@naver.com", "companyB");
            userB = userService.signUp(userDetailInfoB);
        }
        if (userService.findByAuthId("authIdC") == null) {
            UserDetailInfo userDetailInfoC = new UserDetailInfo("authIdC", "passwordC", "nameC", "emailC@naver.com", "companyC");
            userC = userService.signUp(userDetailInfoC);
        }

        friendService.requestFriendApplication(userA.getAuthId(), userB.getAuthId());
        FriendInfo friendApplication = friendService.getReceiveFriendApplication(userA.getAuthId(), userB.getAuthId(), FriendshipState.REQUEST);
        friendService.responseAccept(friendApplication.getId());

        LocalDate now = LocalDate.now();
        monthlyPeriod = Period.createMonthlyPeriod(now);
        weeklyPeriod = Period.createWeeklyPeriod(now);
        dailyPeriod = Period.createDailyPeriod(now);

        if (scheduleService.findAllCategorizedScheduleList(now, userA.getAuthId(), userA.getAuthId()).getDaily().getHabitScheduleList().size() == 0) {
            ScheduleAddDto monthToDoPublicTrue = ScheduleAddDto.createForToDoSchedule(userA, "MonthToDoPublicTrue", OpenScope.PUBLIC, monthlyPeriod, true);
            ScheduleAddDto monthToDoPublicFalse = ScheduleAddDto.createForToDoSchedule(userA, "MonthToDoPublicFalse", OpenScope.PUBLIC, monthlyPeriod, false);
            ScheduleAddDto monthHabitPublic = ScheduleAddDto.createForHabitSchedule(userA, "MonthHabitPublic", OpenScope.PUBLIC, monthlyPeriod);
            ScheduleAddDto monthToDoPrivateTrue = ScheduleAddDto.createForToDoSchedule(userA, "MonthToDoPrivateTrue", OpenScope.PRIVATE, monthlyPeriod, true);
            ScheduleAddDto monthToDoPrivateFalse = ScheduleAddDto.createForToDoSchedule(userA, "MonthToDoPublicTrue", OpenScope.PRIVATE, monthlyPeriod, false);
            ScheduleAddDto monthHabitPrivate = ScheduleAddDto.createForHabitSchedule(userA, "MonthHabitPrivate", OpenScope.PRIVATE, monthlyPeriod);
            ScheduleAddDto monthToDoFriendTrue = ScheduleAddDto.createForToDoSchedule(userA, "MonthToDoFriendTrue", OpenScope.FRIEND, monthlyPeriod, true);
            ScheduleAddDto monthToDoFriendFalse = ScheduleAddDto.createForToDoSchedule(userA, "MonthToDoFriendTrue", OpenScope.FRIEND, monthlyPeriod, false);
            ScheduleAddDto monthHabitFriend = ScheduleAddDto.createForHabitSchedule(userA, "MonthHabitFriend", OpenScope.FRIEND, monthlyPeriod);

            scheduleService.addSchedule(monthToDoPublicTrue);
            scheduleService.addSchedule(monthToDoPublicFalse);
            scheduleService.addSchedule(monthHabitPublic);
            scheduleService.addSchedule(monthToDoPrivateFalse);
            scheduleService.addSchedule(monthToDoPrivateTrue);
            scheduleService.addSchedule(monthHabitPrivate);
            scheduleService.addSchedule(monthToDoFriendFalse);
            scheduleService.addSchedule(monthToDoFriendTrue);
            scheduleService.addSchedule(monthHabitFriend);

            ScheduleAddDto weekToDoPublicTrue = ScheduleAddDto.createForToDoSchedule(userA, "weekToDoPublicTrue", OpenScope.PUBLIC, weeklyPeriod, true);
            ScheduleAddDto weekToDoPublicFalse = ScheduleAddDto.createForToDoSchedule(userA, "weekToDoPublicFalse", OpenScope.PUBLIC, weeklyPeriod, false);
            ScheduleAddDto weekHabitPublic = ScheduleAddDto.createForHabitSchedule(userA, "weekHabitPublic", OpenScope.PUBLIC, weeklyPeriod);
            ScheduleAddDto weekToDoPrivateTrue = ScheduleAddDto.createForToDoSchedule(userA, "weekToDoPrivateTrue", OpenScope.PRIVATE, weeklyPeriod, true);
            ScheduleAddDto weekToDoPrivateFalse = ScheduleAddDto.createForToDoSchedule(userA, "weekToDoPublicTrue", OpenScope.PRIVATE, weeklyPeriod, false);
            ScheduleAddDto weekHabitPrivate = ScheduleAddDto.createForHabitSchedule(userA, "weekHabitPrivate", OpenScope.PRIVATE, weeklyPeriod);

            ScheduleAddDto weekToDoFriendTrue = ScheduleAddDto.createForToDoSchedule(userA, "weekToDoFriendTrue", OpenScope.FRIEND, weeklyPeriod, true);
            ScheduleAddDto weekToDoFriendFalse = ScheduleAddDto.createForToDoSchedule(userA, "weekToDoFriendTrue", OpenScope.FRIEND, weeklyPeriod, false);
            ScheduleAddDto weekHabitFriend = ScheduleAddDto.createForHabitSchedule(userA, "weekHabitFriend", OpenScope.FRIEND, weeklyPeriod);
            scheduleService.addSchedule(weekToDoPublicTrue);
            scheduleService.addSchedule(weekToDoPublicFalse);
            scheduleService.addSchedule(weekHabitPublic);
            scheduleService.addSchedule(weekToDoPrivateTrue);
            scheduleService.addSchedule(weekToDoPrivateFalse);
            scheduleService.addSchedule(weekHabitPrivate);
            scheduleService.addSchedule(weekToDoFriendTrue);
            scheduleService.addSchedule(weekToDoFriendFalse);
            scheduleService.addSchedule(weekHabitFriend);

            ScheduleAddDto dayToDoPublicTrue = ScheduleAddDto.createForToDoSchedule(userA, "dayToDoPublicTrue", OpenScope.PUBLIC, dailyPeriod, true);
            ScheduleAddDto dayToDoPublicFalse = ScheduleAddDto.createForToDoSchedule(userA, "dayToDoPublicFalse", OpenScope.PUBLIC, dailyPeriod, false);
            ScheduleAddDto dayHabitPublic = ScheduleAddDto.createForHabitSchedule(userA, "dayHabitPublic", OpenScope.PUBLIC, dailyPeriod);
            ScheduleAddDto dayToDoPrivateTrue = ScheduleAddDto.createForToDoSchedule(userA, "dayToDoPrivateTrue", OpenScope.PRIVATE, dailyPeriod, true);
            ScheduleAddDto dayToDoPrivateFalse = ScheduleAddDto.createForToDoSchedule(userA, "dayToDoPublicTrue", OpenScope.PRIVATE, dailyPeriod, false);
            ScheduleAddDto dayHabitPrivate = ScheduleAddDto.createForHabitSchedule(userA, "dayHabitPrivate", OpenScope.PRIVATE, dailyPeriod);
            ScheduleAddDto dayToDoFriendTrue = ScheduleAddDto.createForToDoSchedule(userA, "dayToDoFriendTrue", OpenScope.FRIEND, dailyPeriod, true);
            ScheduleAddDto dayToDoFriendFalse = ScheduleAddDto.createForToDoSchedule(userA, "dayToDoFriendTrue", OpenScope.FRIEND, dailyPeriod, false);
            ScheduleAddDto dayHabitFriend = ScheduleAddDto.createForHabitSchedule(userA, "dayHabitFriend", OpenScope.FRIEND, dailyPeriod);

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
    }

    @Test
    public void 할일_월간_일정_추가() {
        Period period = Period.createMonthlyPeriod(2022, 8);

        ScheduleAddDto scheduleAddDTO = ScheduleAddDto.createForToDoSchedule(userA, "title", OpenScope.PUBLIC, period, true);

        scheduleService.addSchedule(scheduleAddDTO);
    }

    @Test
    public void 습관_월간_일정_추가() {
        Period period = Period.createMonthlyPeriod(2022, 8);
        ScheduleAddDto scheduleAddDTO = ScheduleAddDto.createForToDoSchedule(userA, "title", OpenScope.PUBLIC, period, true);

        scheduleService.addSchedule(scheduleAddDTO);
    }

    @Test
    public void 오늘_일정_전체_조회_월간() {
        AllCategorizedScheduleList publicSchedule1 = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), userA.getAuthId());
        Assertions.assertEquals(2, publicSchedule1.getMonthly().getToDoScheduleList().size());
        Assertions.assertEquals(1, publicSchedule1.getMonthly().getHabitScheduleList().size());

        AllCategorizedScheduleList publicSchedule2 = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), userA.getAuthId(), userC.getAuthId());
        Assertions.assertEquals(2, publicSchedule2.getMonthly().getToDoScheduleList().size());
        Assertions.assertEquals(1, publicSchedule2.getMonthly().getHabitScheduleList().size());

        AllCategorizedScheduleList privateSchedule = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), userA.getAuthId(), userA.getAuthId());
        Assertions.assertEquals(6, privateSchedule.getMonthly().getToDoScheduleList().size());
        Assertions.assertEquals(3, privateSchedule.getMonthly().getHabitScheduleList().size());

        AllCategorizedScheduleList friendSchedule = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), userA.getAuthId(), userB.getAuthId());
        Assertions.assertEquals(4, friendSchedule.getMonthly().getToDoScheduleList().size());
        Assertions.assertEquals(2, friendSchedule.getMonthly().getHabitScheduleList().size());
    }

    @Test
    public void 오늘_일정_전체_조회_주간() {
        AllCategorizedScheduleList publicSchedule1 = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), userA.getAuthId());
        Assertions.assertEquals(2, publicSchedule1.getWeekly().getToDoScheduleList().size());
        Assertions.assertEquals(1, publicSchedule1.getWeekly().getHabitScheduleList().size());

        AllCategorizedScheduleList publicSchedule2 = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), userA.getAuthId(), userC.getAuthId());
        Assertions.assertEquals(2, publicSchedule2.getWeekly().getToDoScheduleList().size());
        Assertions.assertEquals(1, publicSchedule2.getWeekly().getHabitScheduleList().size());

        AllCategorizedScheduleList privateSchedule = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), userA.getAuthId(), userA.getAuthId());
        Assertions.assertEquals(6, privateSchedule.getWeekly().getToDoScheduleList().size());
        Assertions.assertEquals(3, privateSchedule.getWeekly().getHabitScheduleList().size());

        AllCategorizedScheduleList friendSchedule = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), userA.getAuthId(), userB.getAuthId());
        Assertions.assertEquals(4, friendSchedule.getWeekly().getToDoScheduleList().size());
        Assertions.assertEquals(2, friendSchedule.getWeekly().getHabitScheduleList().size());
    }

    @Test
    public void 오늘_일정_전체_조회_일간() {
        AllCategorizedScheduleList publicSchedule1 = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), userA.getAuthId());
        Assertions.assertEquals(2, publicSchedule1.getDaily().getToDoScheduleList().size());
        Assertions.assertEquals(1, publicSchedule1.getDaily().getHabitScheduleList().size());

        AllCategorizedScheduleList publicSchedule2 = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), userA.getAuthId(), userC.getAuthId());
        Assertions.assertEquals(2, publicSchedule2.getDaily().getToDoScheduleList().size());
        Assertions.assertEquals(1, publicSchedule2.getDaily().getHabitScheduleList().size());

        AllCategorizedScheduleList privateSchedule = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), userA.getAuthId(), userA.getAuthId());
        Assertions.assertEquals(6, privateSchedule.getDaily().getToDoScheduleList().size());
        Assertions.assertEquals(3, privateSchedule.getDaily().getHabitScheduleList().size());

        AllCategorizedScheduleList friendSchedule = scheduleService.findAllCategorizedScheduleList(LocalDate.now(), userA.getAuthId(), userB.getAuthId());
        Assertions.assertEquals(4, friendSchedule.getDaily().getToDoScheduleList().size());
        Assertions.assertEquals(2, friendSchedule.getDaily().getHabitScheduleList().size());
    }

    @Test
    public void 할일_일정_수정(){
        ScheduleAddDto scheduleAddDto = ScheduleAddDto.createForToDoSchedule(userA, "dayToDoPublicTrue", OpenScope.PUBLIC, dailyPeriod, true);
        Schedule schedule = scheduleService.addSchedule(scheduleAddDto);

        ScheduleChangeDto scheduleChangeDto = ScheduleChangeDto.builder()
                .title("UpdateTitle")
                .isCompleted(false).build();
        scheduleService.updateSchedule(schedule.getId(), scheduleChangeDto);

        Assertions.assertEquals(scheduleChangeDto.getTitle(), schedule.getTitle());
        Assertions.assertEquals(scheduleChangeDto.getIsCompleted(), ((ToDoSchedule) schedule).getIsCompleted());
    }

    @Test
    public void 습관_일정_수정(){
        Period dailyPeriod = Period.createDailyPeriod(LocalDate.now());
        ScheduleAddDto scheduleAddDto = ScheduleAddDto.createForHabitSchedule(userA, "dayHabitPublicTrue", OpenScope.PUBLIC, dailyPeriod);
        Schedule schedule = scheduleService.addSchedule(scheduleAddDto);

        ScheduleChangeDto scheduleChangeDto = ScheduleChangeDto.builder()
                .title("UpdateTitle")
                .build();
        scheduleService.updateSchedule(schedule.getId(), scheduleChangeDto);

        Assertions.assertEquals(scheduleChangeDto.getTitle(), schedule.getTitle());
    }

    @Test
    public void 일정_삭제() {
        Period dailyPeriod = Period.createDailyPeriod(LocalDate.now());
        ScheduleAddDto scheduleAddDto = ScheduleAddDto.createForHabitSchedule(userA, "dayHabitPublicTrue", OpenScope.PUBLIC, dailyPeriod);
        Schedule schedule = scheduleService.addSchedule(scheduleAddDto);

        scheduleService.deleteSchedule(userA.getAuthId(), userA.getAuthId(), schedule.getId());

        Optional<Schedule> findById = scheduleRepository.findById(schedule.getId());
        Assertions.assertTrue(findById.isEmpty());
    }
}