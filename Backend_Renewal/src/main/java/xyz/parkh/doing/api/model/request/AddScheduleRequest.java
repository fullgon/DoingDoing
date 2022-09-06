package xyz.parkh.doing.api.model.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import xyz.parkh.doing.domain.schedule.model.OpenScope;
import xyz.parkh.doing.domain.schedule.model.Period;
import xyz.parkh.doing.domain.schedule.model.ScheduleDto;
import xyz.parkh.doing.domain.schedule.model.ScheduleType;
import xyz.parkh.doing.domain.user.entity.User;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddScheduleRequest {
    private String title;
    private OpenScope openScope;
    private Period period;
    private Boolean isCompleted;
    private ScheduleType scheduleType;

    public ScheduleDto convert(User user) {
        if (scheduleType == ScheduleType.HABIT)
            return ScheduleDto.createForHabitSchedule(user, title, openScope, period);
        else if (scheduleType == ScheduleType.TODO) {
            return ScheduleDto.createForToDoSchedule(user, title, openScope, period, isCompleted);
        }
        throw new NullPointerException("일정 타입이 존재하지 않습니다.");
    }

}
