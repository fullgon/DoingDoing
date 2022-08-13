package xyz.parkh.doing.domain.model.schedule;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import xyz.parkh.doing.domain.entity.schedule.HabitSchedule;
import xyz.parkh.doing.domain.entity.schedule.Schedule;
import xyz.parkh.doing.domain.entity.schedule.ToDoSchedule;
import xyz.parkh.doing.domain.entity.user.User;

@Getter
@ToString
public class ScheduleDTO {
    private User user;
    private String title;
    private String content;
    private OpenScope openScope;
    private Period period;
    private Boolean isCompleted;
    private ScheduleType scheduleType;

    public ScheduleDTO() {
    }

    @Builder
    public ScheduleDTO(User user, String title, String content, OpenScope openScope, Period period, Boolean isCompleted, ScheduleType scheduleType) {
        this.user = user;
        this.title = title;
        this.content = content;
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
            toDoSchedule = ToDoSchedule.createMonthlyToDoSchedule(user, title, content, openScope, isCompleted);
        } else if (periodType == PeriodType.WEEK) {
            toDoSchedule = ToDoSchedule.createWeeklyToDoSchedule(user, title, content, openScope, isCompleted);
        } else if (periodType == PeriodType.DAY) {
            toDoSchedule = ToDoSchedule.createDailyToDoSchedule(user, title, content, openScope, isCompleted);
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
            habitSchedule = HabitSchedule.createMonthlyHabitSchedule(user, title, content, openScope);
        } else if (periodType == PeriodType.WEEK) {
            habitSchedule = HabitSchedule.createWeeklyHabitSchedule(user, title, content, openScope);
        } else if (periodType == PeriodType.DAY) {
            habitSchedule = HabitSchedule.createDailyHabitSchedule(user, title, content, openScope);
        }
        return habitSchedule;
    }

}
