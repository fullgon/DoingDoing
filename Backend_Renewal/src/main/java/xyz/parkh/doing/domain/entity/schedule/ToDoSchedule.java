package xyz.parkh.doing.domain.entity.schedule;

import lombok.*;
import xyz.parkh.doing.domain.entity.user.User;
import xyz.parkh.doing.domain.model.schedule.OpenScope;
import xyz.parkh.doing.domain.model.schedule.Period;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ToDoSchedule extends Schedule {
    private Boolean isCompleted;

    @Builder
    public ToDoSchedule(User user, String title, String content, OpenScope openScope, Period period, Boolean isCompleted) {
        super(user, title, content, openScope, period);
        this.isCompleted = isCompleted;
    }
}
