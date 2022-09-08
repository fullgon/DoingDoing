package xyz.parkh.doing.domain.schedule.entity;

import lombok.*;
import xyz.parkh.doing.domain.schedule.model.ScheduleChangeDto;
import xyz.parkh.doing.domain.schedule.model.ScheduleType;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.schedule.model.OpenScope;
import xyz.parkh.doing.domain.schedule.model.Period;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@DiscriminatorValue("TODO")
public class ToDoSchedule extends Schedule {
    private Boolean isCompleted;

    public ToDoSchedule(User user, String title, OpenScope openScope, Period period, Boolean isCompleted) {
        super(user, title, openScope, period, ScheduleType.TODO);
        this.isCompleted = isCompleted;
    }

    @Override
    public void updateSchedule(ScheduleChangeDto scheduleChangeDto) {
        super.updateSchedule(scheduleChangeDto);
        if (scheduleChangeDto.getIsCompleted() != null) {
            this.isCompleted = scheduleChangeDto.getIsCompleted();
        }
    }
}
