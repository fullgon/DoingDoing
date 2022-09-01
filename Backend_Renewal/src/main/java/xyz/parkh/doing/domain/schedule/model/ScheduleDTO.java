package xyz.parkh.doing.domain.schedule.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import xyz.parkh.doing.domain.schedule.entity.HabitSchedule;
import xyz.parkh.doing.domain.schedule.entity.Schedule;
import xyz.parkh.doing.domain.schedule.entity.ToDoSchedule;
import xyz.parkh.doing.domain.user.entity.User;

@Getter
@ToString
public class ScheduleDTO {
    private User user;
    private String title;
    private OpenScope openScope;
    private Period period;
    private Boolean isCompleted;
    private ScheduleType scheduleType;

    public ScheduleDTO() {
    }

    @Builder
    public ScheduleDTO(User user, String title, OpenScope openScope, Period period, Boolean isCompleted, ScheduleType scheduleType) {
        this.user = user;
        this.title = title;
        this.openScope = openScope;
        this.period = period;
        this.isCompleted = isCompleted;
        this.scheduleType = scheduleType;
    }

    public Schedule convertSchedule() {
        if (scheduleType == null) {
            throw new NullPointerException();
        }
        Schedule schedule = null;

        if (scheduleType == ScheduleType.HABIT) {
            schedule = convertHabitSchedule();
        } else if (scheduleType == ScheduleType.TODO) {
            schedule = convertToDoSchedule();
        }
        return schedule;
    }

    private ToDoSchedule convertToDoSchedule() {
        PeriodType periodType = period.getPeriodType();
        if (periodType == null) {
            throw new NullPointerException();
        }

        ToDoSchedule toDoSchedule = null;
        if (periodType == PeriodType.MONTH) {
            toDoSchedule = ToDoSchedule.createTodayMonthly(user, title, openScope, isCompleted);
        } else if (periodType == PeriodType.WEEK) {
            toDoSchedule = ToDoSchedule.createTodayWeekly(user, title,  openScope, isCompleted);
        } else if (periodType == PeriodType.DAY) {
            toDoSchedule = ToDoSchedule.createTodayDaily(user, title,  openScope, isCompleted);
        }
        return toDoSchedule;
    }

    private HabitSchedule convertHabitSchedule() {
        PeriodType periodType = period.getPeriodType();
        if (periodType == null) {
            throw new NullPointerException();
        }

        HabitSchedule habitSchedule = null;
        if (periodType == PeriodType.MONTH) {
            habitSchedule = HabitSchedule.createTodayMonthly(user, title, openScope);
        } else if (periodType == PeriodType.WEEK) {
            habitSchedule = HabitSchedule.createTodayWeekly(user, title, openScope);
        } else if (periodType == PeriodType.DAY) {
            habitSchedule = HabitSchedule.createTodayDaily(user, title, openScope);
        }
        return habitSchedule;
    }

}
