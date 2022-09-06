package xyz.parkh.doing.domain.schedule.model;

import lombok.Getter;
import lombok.ToString;
import xyz.parkh.doing.domain.schedule.entity.HabitSchedule;
import xyz.parkh.doing.domain.schedule.entity.ToDoSchedule;
import xyz.parkh.doing.domain.user.entity.User;

@Getter
@ToString
public class ScheduleDto {
    private User user;
    private String title;
    private OpenScope openScope;
    private Period period;
    private Boolean isCompleted;
    private ScheduleType scheduleType;

    public ScheduleDto(User user, String title, OpenScope openScope, Period period, ScheduleType scheduleType) {
        this.user = user;
        this.title = title;
        this.openScope = openScope;
        this.period = period;
        this.scheduleType = scheduleType;
    }

    public ScheduleDto(User user, String title, OpenScope openScope, Period period, Boolean isCompleted, ScheduleType scheduleType) {
        this.user = user;
        this.title = title;
        this.openScope = openScope;
        this.period = period;
        this.isCompleted = isCompleted;
        this.scheduleType = scheduleType;
    }

    public ToDoSchedule convertToDoSchedule() {
        return new ToDoSchedule(user, title, openScope, period, isCompleted);
    }

    public HabitSchedule convertHabitSchedule() {
        return new HabitSchedule(user, title, openScope, period);
    }

    public static ScheduleDto createForToDoSchedule(User user, String title, OpenScope openScope, Period period, Boolean isCompleted) {
        return new ScheduleDto(user, title, openScope, period, isCompleted, ScheduleType.TODO);
    }

    public static ScheduleDto createForHabitSchedule(User user, String title, OpenScope openScope, Period period) {
        return new ScheduleDto(user, title, openScope, period, ScheduleType.HABIT);
    }
}
