package xyz.parkh.doing.domain.entity.schedule;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import xyz.parkh.doing.domain.entity.user.User;
import xyz.parkh.doing.domain.model.schedule.OpenScope;
import xyz.parkh.doing.domain.model.schedule.Period;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HabitSchedule extends Schedule {
    @Builder
    public HabitSchedule(User user, String title, String content, OpenScope openScope, Period period) {
        super.setUser(user);
        super.setContent(content);
        super.setOpenScope(openScope);
        super.setPeriod(period);
        super.setTitle(title);
    }
}
