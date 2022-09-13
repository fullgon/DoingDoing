package xyz.parkh.doing.domain.schedule.model;

import lombok.Getter;
import lombok.ToString;
import xyz.parkh.doing.domain.schedule.entity.HabitSchedule;
import xyz.parkh.doing.domain.schedule.entity.ToDoSchedule;
import xyz.parkh.doing.domain.user.entity.User;

@Getter
@ToString
public class ScheduleAddDto {
    private User requester;
    private User owner;
    private String title;
    private OpenScope openScope;
    private Period period;
    private Boolean isCompleted;
    private ScheduleType scheduleType;

    private ScheduleAddDto(User requester, User owner, String title, OpenScope openScope, Period period, Boolean isCompleted, ScheduleType scheduleType) {
        this.requester = requester;
        this.owner = owner;
        this.title = title;
        this.openScope = openScope;
        this.period = period;
        this.isCompleted = isCompleted;
        this.scheduleType = scheduleType;
    }

    private ScheduleAddDto(User requester, User owner, String title, OpenScope openScope, Period period, ScheduleType scheduleType) {
        this.requester = requester;
        this.owner = owner;
        this.title = title;
        this.openScope = openScope;
        this.period = period;
        this.scheduleType = scheduleType;
    }

    public ToDoSchedule convertToDoSchedule() {
        return new ToDoSchedule(owner, title, openScope, period, isCompleted);
    }

    public HabitSchedule convertHabitSchedule() {
        return new HabitSchedule(owner, title, openScope, period);
    }

    public static ScheduleAddDto createForToDoSchedule(User requester, User owner, String title, OpenScope openScope, Period period, Boolean isCompleted) {
        return new ScheduleAddDto(requester, owner, title, openScope, period, isCompleted, ScheduleType.TODO);
    }

    public static ScheduleAddDto createForHabitSchedule(User requester, User owner, String title, OpenScope openScope, Period period) {
        return new ScheduleAddDto(requester, owner, title, openScope, period, ScheduleType.HABIT);
    }
}
