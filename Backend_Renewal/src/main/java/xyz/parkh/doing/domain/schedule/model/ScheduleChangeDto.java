package xyz.parkh.doing.domain.schedule.model;

import lombok.Builder;
import lombok.Getter;
import xyz.parkh.doing.domain.user.entity.User;

@Getter
public class ScheduleChangeDto {

    private Long scheduleId;
    private User requester;
    private String title;
    private OpenScope openScope;
    private Period period;
    private Boolean isCompleted;
    private ScheduleType scheduleType;

    @Builder
    public ScheduleChangeDto(Long scheduleId, User requester, String title, OpenScope openScope, Period period, Boolean isCompleted, ScheduleType scheduleType) {
        this.scheduleId = scheduleId;
        this.requester = requester;
        this.title = title;
        this.openScope = openScope;
        this.period = period;
        this.isCompleted = isCompleted;
        this.scheduleType = scheduleType;
    }
}
