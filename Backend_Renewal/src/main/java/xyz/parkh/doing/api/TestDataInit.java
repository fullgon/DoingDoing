package xyz.parkh.doing.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import xyz.parkh.doing.domain.friend.model.FriendInfo;
import xyz.parkh.doing.domain.friend.model.FriendshipState;
import xyz.parkh.doing.domain.friend.service.FriendService;
import xyz.parkh.doing.domain.schedule.model.OpenScope;
import xyz.parkh.doing.domain.schedule.model.Period;
import xyz.parkh.doing.domain.schedule.model.ScheduleDto;
import xyz.parkh.doing.domain.schedule.service.ScheduleService;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.user.model.UserDetailInfo;
import xyz.parkh.doing.domain.user.service.UserService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final UserService userService;
    private final FriendService friendService;
    private final ScheduleService scheduleService;

    @PostConstruct
    public void userInit() throws Exception {
        if (userService.findByAuthId("authIdA") == null) {
            UserDetailInfo userDetailInfoA = new UserDetailInfo("authIdA", "passwordA", "nameA", "emailA@naver.com", "companyA");
            userService.signUp(userDetailInfoA);
        }
        if (userService.findByAuthId("authIdB") == null) {
            UserDetailInfo userDetailInfoB = new UserDetailInfo("authIdB", "passwordB", "nameB", "emailB@naver.com", "companyB");
            userService.signUp(userDetailInfoB);
        }
        if (userService.findByAuthId("authIdC") == null) {
            UserDetailInfo userDetailInfoC = new UserDetailInfo("authIdC", "passwordC", "nameC", "emailC@naver.com", "companyC");
            userService.signUp(userDetailInfoC);
        }

        User userA = userService.findByAuthId("authIdA");
        User userB = userService.findByAuthId("authIdB");

        friendService.requestFriendApplication(userA.getAuthId(), userB.getAuthId());
        FriendInfo friendApplication = friendService.getReceiveFriendApplication(userA.getAuthId(), userB.getAuthId(), FriendshipState.REQUEST);
        friendService.responseAccept(friendApplication.getId());

        LocalDate now = LocalDate.now();
        Period monthlyPeriod = Period.createMonthlyPeriod(now);
        Period weeklyPeriod = Period.createWeeklyPeriod(now);
        Period dailyPeriod = Period.createDailyPeriod(now);

        if (scheduleService.findAllCategorizedScheduleList(now, userA.getAuthId(), userA.getAuthId()).getDaily().getHabitScheduleList().size() == 0) {
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
    }
}
