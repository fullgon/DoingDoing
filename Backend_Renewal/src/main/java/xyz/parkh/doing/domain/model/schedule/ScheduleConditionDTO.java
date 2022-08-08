package xyz.parkh.doing.domain.model.schedule;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class ScheduleConditionDTO {
    private String authId;
    private Period period;
    private ScheduleType scheduleType;
    private OpenScope openScope;

    @Builder
    public ScheduleConditionDTO(String authId, Period period, ScheduleType scheduleType, OpenScope openScope) {
        this.authId = authId;
        this.period = period;
        this.scheduleType = scheduleType;
        this.openScope = openScope;
    }
}
