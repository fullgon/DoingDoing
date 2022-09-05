package xyz.parkh.doing.domain.schedule.model;

import lombok.Getter;

@Getter
public class ShortHabitSchedule {
    private Long id;
    private String title;
    private OpenScope openScope;

    public ShortHabitSchedule(Long id, String title, OpenScope openScope) {
        this.id = id;
        this.title = title;
        this.openScope = openScope;
    }
}
