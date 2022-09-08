package xyz.parkh.doing.domain.schedule.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ScheduleChangeDto {
    private String title;
    private OpenScope openScope;
    private Period period;
    private Boolean isCompleted;
    private ScheduleType scheduleType;

    @Builder
    public ScheduleChangeDto(String title, OpenScope openScope, Period period, Boolean isCompleted, ScheduleType scheduleType) {
        this.title = title;
        this.openScope = openScope;
        this.period = period;
        this.isCompleted = isCompleted;
        this.scheduleType = scheduleType;
    }
}
