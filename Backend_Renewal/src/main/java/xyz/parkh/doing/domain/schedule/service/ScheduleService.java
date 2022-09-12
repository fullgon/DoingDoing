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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final UserService userService;
    private final ScheduleRepository scheduleRepository;
    private final FriendService friendService;

    public Schedule findByScheduleId(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).get();
        if (schedule == null) {
            throw new NullPointerException("존재하는 일정이 없습니다");
        }
        return schedule;
    }

    // 본인 일정 생성
    public Schedule addSchedule(String requesterId, ScheduleAddDto scheduleAddDTO) {
        Schedule schedule = null;
        String targetId = scheduleAddDTO.getUser().getAuthId();

        if (impossibleAdd(requesterId, targetId)) {
            throw new NullPointerException("추가할 수 있는 권한이 없습니다.");
        }

        if (scheduleAddDTO.getScheduleType() == ScheduleType.HABIT) {
            schedule = scheduleAddDTO.convertHabitSchedule();
        } else if (scheduleAddDTO.getScheduleType() == ScheduleType.TODO) {
            schedule = scheduleAddDTO.convertToDoSchedule();
        }

        Schedule saveSchedule = scheduleRepository.save(schedule);

        return saveSchedule;
    }

    private boolean impossibleAdd(String requesterId, String targetId) {
        return !requesterId.equals(targetId);
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
    public List<Schedule> findAccessibleSchedule(ScheduleConditionDTO scheduleConditionDTO) {
        String targetId = scheduleConditionDTO.getTargetId();
        String requesterId = scheduleConditionDTO.getRequesterId();
        Period period = scheduleConditionDTO.getPeriod();

        User target = userService.findByAuthId(targetId);
        if (target == null) {
            throw new NullPointerException("조회하려는 사용자가 없습니다.");
        }

        User requester = userService.findByAuthId(requesterId);

        if (target.equals(requester)) {
            return scheduleRepository.findAllByUserAndPeriod(target, period);
        }

        if (friendService.isFriend(targetId, requesterId)) {
            return scheduleRepository.findAllByUserAndPeriod(target, period).stream()
                    .filter(schedule -> schedule.getOpenScope() == OpenScope.PUBLIC
                            || schedule.getOpenScope() == OpenScope.FRIEND)
                    .collect(Collectors.toList());
        }

        return scheduleRepository.findAllByUserAndPeriod(target, period).stream()
                .filter(schedule -> schedule.getOpenScope() == OpenScope.PUBLIC)
                .collect(Collectors.toList());
    }


    public AllCategorizedScheduleList findAllCategorizedScheduleList(LocalDate localDate, String targetId, String requesterId) {
        int year = localDate.getYear();
        int month = localDate.getMonth().getValue();
        int day = localDate.getDayOfMonth();

        Period monthlyPeriod = Period.createMonthlyPeriod(year, month);
        Period weeklyPeriod = Period.createWeeklyPeriod(localDate);
        Period dailyPeriod = Period.createDailyPeriod(year, month, day);

        ScheduleConditionDTO monthlyCondition = new ScheduleConditionDTO(targetId, requesterId, monthlyPeriod);
        CategorizedScheduleList monthlySchedule = findCategorizedScheduleList(monthlyCondition);

        ScheduleConditionDTO weeklyCondition = new ScheduleConditionDTO(targetId, requesterId, weeklyPeriod);
        CategorizedScheduleList weeklySchedule = findCategorizedScheduleList(weeklyCondition);

        ScheduleConditionDTO dailyCondition = new ScheduleConditionDTO(targetId, requesterId, dailyPeriod);
        CategorizedScheduleList dailySchedule = findCategorizedScheduleList(dailyCondition);

        AllCategorizedScheduleList scheduleList = new AllCategorizedScheduleList(monthlySchedule, weeklySchedule, dailySchedule);
        return scheduleList;
    }

    public AllCategorizedScheduleList findAllCategorizedScheduleList(LocalDate localDate, String targetId) {
        return findAllCategorizedScheduleList(localDate, targetId, null);

    }

    // 일정 타입별 일정 조회
    public CategorizedScheduleList findCategorizedScheduleList(ScheduleConditionDTO scheduleConditionDto) {
        List<Schedule> scheduleList = findAccessibleSchedule(scheduleConditionDto);
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
    public void updateSchedule(Long scheduleId, ScheduleChangeDto scheduleChangeDto) {
        // TODO 없는 일정 조회 시 에러 발생 Optional 공부 후 처리 변경
        Schedule schedule = scheduleRepository.findById(scheduleId).get();

        if (schedule == null) {
            throw new NullPointerException();
        }
        if (schedule.getScheduleType() == ScheduleType.HABIT) {
            if (scheduleChangeDto.getIsCompleted() != null) {
                throw new NullPointerException("습관 일정은 완료 여부를 수정할 수 없습니다.");
            }
            ((HabitSchedule) schedule).updateSchedule(scheduleChangeDto);
        } else if (schedule.getScheduleType() == ScheduleType.TODO) {
            ((ToDoSchedule) schedule).updateSchedule(scheduleChangeDto);
        }
    }

    // 일정 삭제
    public void deleteSchedule(Long scheduleId, String targetId, String requesterId) {
        if (targetId.equals(requesterId)) {
            Optional<Schedule> findSchedule = scheduleRepository.findById(scheduleId);
            if (findSchedule.isEmpty()) {
                throw new NullPointerException("삭제할 일정이 없습니다.");
            }
            scheduleRepository.delete(findSchedule.get());
        } else {
            throw new NullPointerException("삭제 권한이 없습니다.");
        }
    }
}