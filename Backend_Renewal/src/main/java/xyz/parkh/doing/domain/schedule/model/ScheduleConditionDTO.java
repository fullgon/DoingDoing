package xyz.parkh.doing.domain.schedule.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class ScheduleConditionDTO {
    private String targetId;
    private String requesterId;
    private Period period;

    public ScheduleConditionDTO(String targetId, String requesterId, Period period) {
        this.targetId = targetId;
        this.requesterId = requesterId;
        this.period = period;
    }
}