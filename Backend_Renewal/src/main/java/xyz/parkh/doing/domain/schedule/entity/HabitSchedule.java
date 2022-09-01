package xyz.parkh.doing.domain.schedule.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.schedule.model.OpenScope;
import xyz.parkh.doing.domain.schedule.model.Period;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HabitSchedule extends Schedule {
    public HabitSchedule(User user, String title, OpenScope openScope, Period period) {
        super(user, title, openScope, period);
    }

    public static HabitSchedule createTodayMonthly(User user, String title, OpenScope openScope) {
        Period period = Period.createTodayMonthlyPeriod();
        return new HabitSchedule(user, title, openScope, period);
    }

    public static HabitSchedule createTodayWeekly(User user, String title, OpenScope openScope) {
        Period period = Period.createTodayWeeklyPeriod();
        return new HabitSchedule(user, title, openScope, period);
    }

    public static HabitSchedule createTodayDaily(User user, String title, OpenScope openScope) {
        Period period = Period.createTodayDailyPeriod();
        return new HabitSchedule(user, title, openScope, period);
    }
}
