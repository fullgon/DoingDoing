package xyz.parkh.doing.domain.entity.schedule;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xyz.parkh.doing.domain.entity.user.User;
import xyz.parkh.doing.domain.model.schedule.OpenScope;
import xyz.parkh.doing.domain.model.schedule.Period;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HabitSchedule extends Schedule {
    public HabitSchedule(User user, String title, String content, OpenScope openScope, Period period) {
        super(user, title, content, openScope, period);
    }

    public static HabitSchedule createMonthlyHabitSchedule(User user, String title, String content, OpenScope openScope) {
        Period period = Period.createMonthlyPeriod();
        return new HabitSchedule(user, title, content, openScope, period);
    }

    public static HabitSchedule createWeeklyHabitSchedule(User user, String title, String content, OpenScope openScope) {
        Period period = Period.createWeeklyPeriod();
        return new HabitSchedule(user, title, content, openScope, period);
    }

    public static HabitSchedule createDailyHabitSchedule(User user, String title, String content, OpenScope openScope) {
        Period period = Period.createDailyPeriod();
        return new HabitSchedule(user, title, content, openScope, period);
    }
}
