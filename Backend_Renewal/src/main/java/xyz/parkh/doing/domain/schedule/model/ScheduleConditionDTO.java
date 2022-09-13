package xyz.parkh.doing.domain.schedule.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import xyz.parkh.doing.domain.user.entity.User;

@ToString
@Getter
@NoArgsConstructor
public class ScheduleConditionDTO {
    private User target;
    private User requester;
    private Period period;

    public ScheduleConditionDTO(User target, User requester, Period period) {
        this.target = target;
        this.requester = requester;
        this.period = period;
    }
}