package xyz.parkh.doing.domain.schedule.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.schedule.model.OpenScope;
import xyz.parkh.doing.domain.schedule.model.Period;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ToDoSchedule extends Schedule {
    private Boolean isCompleted;

    public ToDoSchedule(User user, String title, OpenScope openScope, Period period, Boolean isCompleted) {
        super(user, title, openScope, period);

        this.isCompleted = isCompleted;
    }

    public static ToDoSchedule createMonthlyToDoSchedule(User user, String title, OpenScope openScope, Boolean isCompleted) {
        Period period = Period.createMonthlyPeriod();
        return new ToDoSchedule(user, title, openScope, period, isCompleted);
    }

    public static ToDoSchedule createWeeklyToDoSchedule(User user, String title, OpenScope openScope, Boolean isCompleted) {
        Period period = Period.createWeeklyPeriod();
        return new ToDoSchedule(user, title, openScope, period, isCompleted);
    }

    public static ToDoSchedule createDailyToDoSchedule(User user, String title, OpenScope openScope, Boolean isCompleted) {
        Period period = Period.createDailyPeriod();
        return new ToDoSchedule(user, title, openScope, period, isCompleted);
    }
}
