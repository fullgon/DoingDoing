package xyz.parkh.doing.domain.model.schedule;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import xyz.parkh.doing.domain.entity.schedule.HabitSchedule;
import xyz.parkh.doing.domain.entity.schedule.Schedule;
import xyz.parkh.doing.domain.entity.schedule.ToDoSchedule;
import xyz.parkh.doing.domain.entity.user.User;

@Getter
@ToString
public class ScheduleDTO {
    private User user;
    private String title;
    private String content;
    private OpenScope openScope;
    private Period period;
    private Boolean isCompleted;

    private ScheduleType scheduleType;

    public ScheduleDTO() {
    }

    @Builder
    public ScheduleDTO(User user, String title, String content, OpenScope openScope, Period period, Boolean isCompleted, ScheduleType scheduleType) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.openScope = openScope;
        this.period = period;
        this.isCompleted = isCompleted;
        this.scheduleType = scheduleType;
    }

    public ToDoSchedule convertToDoSchedule() {
        return ToDoSchedule.builder().user(user).title(title)
                .content(content).openScope(openScope).period(period)
                .isCompleted(isCompleted).build();
    }

    public HabitSchedule convertHabitSchedule() {
        return HabitSchedule.builder().user(user).title(title)
                .content(content).openScope(openScope).period(period)
                .build();
    }

}
