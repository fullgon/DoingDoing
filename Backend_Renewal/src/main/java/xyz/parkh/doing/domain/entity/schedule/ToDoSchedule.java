package xyz.parkh.doing.domain.entity.schedule;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.parkh.doing.domain.entity.user.User;
import xyz.parkh.doing.domain.model.schedule.OpenScope;
import xyz.parkh.doing.domain.model.schedule.Period;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ToDoSchedule extends Schedule {
    private Boolean isCompleted;

    @Builder
    public ToDoSchedule(User user, String title, String content, OpenScope openScope, Period period, Boolean isCompleted) {
        super.setUser(user);
        super.setContent(content);
        super.setOpenScope(openScope);
        super.setPeriod(period);
        super.setTitle(title);
        this.isCompleted = isCompleted;
    }
}
