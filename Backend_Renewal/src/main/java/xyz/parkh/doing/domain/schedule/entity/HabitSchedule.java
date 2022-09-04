package xyz.parkh.doing.domain.schedule.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xyz.parkh.doing.domain.schedule.model.ScheduleType;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.schedule.model.OpenScope;
import xyz.parkh.doing.domain.schedule.model.Period;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@DiscriminatorValue("HABIT")
public class HabitSchedule extends Schedule {
    public HabitSchedule(User user, String title, OpenScope openScope, Period period) {
        super(user, title, openScope, period, ScheduleType.HABIT);
    }
}
