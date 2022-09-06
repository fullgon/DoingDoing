package xyz.parkh.doing.domain.schedule.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.friend.service.FriendService;
import xyz.parkh.doing.domain.schedule.entity.HabitSchedule;
import xyz.parkh.doing.domain.schedule.entity.Schedule;
import xyz.parkh.doing.domain.schedule.entity.ToDoSchedule;
import xyz.parkh.doing.domain.schedule.model.*;
import xyz.parkh.doing.domain.schedule.repository.ScheduleRepository;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.user.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final UserService userService;
    private final ScheduleRepository scheduleRepository;
    private final FriendService friendService;

    // 일정 생성
    public void addSchedule(ScheduleDto scheduleDTO) {
        Schedule schedule = null;

        if (scheduleDTO.getScheduleType() == ScheduleType.HABIT) {
            schedule = scheduleDTO.convertHabitSchedule();
        } else if (scheduleDTO.getScheduleType() == ScheduleType.TODO) {
            schedule = scheduleDTO.convertToDoSchedule();
        }

        if (schedule == null) {
            throw new NullPointerException();
        }
        scheduleRepository.save(schedule);
    }

    // 일정 목록 조회
//    public List<Schedule> findScheduleList(ScheduleConditionDTO scheduleConditionDto) {
//        // 요청하는 사람과 일정 주인의 관계 확인
//        // - OpenScope.PRIVATE : 본인
//        // - OpenScope.FRIEND : 본인, 친구
//        // - OpenScope.PUBLIC : 모든 사용자 (본인, 친구, 친구X, 로그인 안 한 사용자)
//
//        // 일정 조회 기간 확인
//        //    일정 기간 타입 확인
//        //    - PeriodType.YEAR, PeriodType.MONTH, PeriodType.DAY
//        //    일정 기간 확인
//        //    - Period.year, Period.week, Period.day
//
//        // 일정 타입 확인
//        // - ScheduleType.HABIT : 습관
//        // - ScheduleType.TODO : 할일
//
//        User user = userService.findByAuthId(scheduleConditionDto.getAuthId());
//        Period period = scheduleConditionDto.getPeriod();
//        OpenScope openScope = scheduleConditionDto.getOpenScope();
//
//        List<Schedule> scheduleList = scheduleRepository.findAllByUserAndPeriodAndOpenScopeAndScheduleType(user, period, openScope, scheduleType);
//
//        return scheduleList;
//    }

    // 권한에 따른 일정 조회
    public List<Schedule> findAccessibleSchedule(String targetId, String requesterId, Period period) {
        User target = userService.findByAuthId(targetId);
        User requester = userService.findByAuthId(requesterId);

        if (target.equals(requester)) {
            return scheduleRepository.findAllByUserAndPeriod(target, period);
        }

        if (friendService.isFriend(targetId, requesterId)) {
            return scheduleRepository.findAllByUserAndPeriod(target, period).stream()
                    .filter(schedule -> schedule.getOpenScope() == OpenScope.PUBIC
                            || schedule.getOpenScope() == OpenScope.FRIEND)
                    .collect(Collectors.toList());

        }

        return scheduleRepository.findAllByUserAndPeriod(target, period).stream()
                .filter(schedule -> schedule.getOpenScope() == OpenScope.PUBIC)
                .collect(Collectors.toList());
    }

    // 일정 타입별 일정 조회
    public CategorizedScheduleList findCategorizedScheduleList(ScheduleConditionDTO scheduleConditionDto) {
        String requesterId = scheduleConditionDto.getRequesterId();
        String targetId = scheduleConditionDto.getTargetId();
        Period period = scheduleConditionDto.getPeriod();

        List<Schedule> scheduleList = findAccessibleSchedule(targetId, requesterId, period);
        List<Schedule> habitList = scheduleList.stream()
                .filter(schedule -> schedule.getScheduleType() == ScheduleType.HABIT)
                .collect(Collectors.toList());

        List<ShortHabitSchedule> shortHabitList = new ArrayList<>();
        for (Schedule schedule : habitList) {
            HabitSchedule habit = (HabitSchedule) schedule;
            ShortHabitSchedule shortHabit = new ShortHabitSchedule(habit.getId(), habit.getTitle(), habit.getOpenScope());
            shortHabitList.add(shortHabit);
        }

        List<Schedule> toDoList = scheduleList.stream()
                .filter(schedule -> schedule.getScheduleType() == ScheduleType.TODO)
                .collect(Collectors.toList());

        List<ShortToDoSchedule> shortToDoList = new ArrayList<>();
        for (Schedule schedule : toDoList) {
            ToDoSchedule toDo = (ToDoSchedule) schedule;
            ShortToDoSchedule shortToDo = new ShortToDoSchedule(toDo.getId(), toDo.getTitle(), toDo.getOpenScope(), toDo.getIsCompleted());
            shortToDoList.add(shortToDo);
        }

        return new CategorizedScheduleList(shortHabitList, shortToDoList);
    }


    // 일정 수정

    // 일정 삭제


}
