package xyz.parkh.doing.domain.schedule.model;

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
    private OpenScope openScope;

    @Builder
    public ScheduleConditionDTO(String authId, Period period, OpenScope openScope) {
        this.authId = authId;
        this.period = period;
        this.openScope = openScope;
    }
}
