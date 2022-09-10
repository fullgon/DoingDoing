package xyz.parkh.doing.domain.schedule.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ShortToDoSchedule {
    private Long id;
    private String title;
    private OpenScope openScope;
    private boolean isCompleted;

    public ShortToDoSchedule(Long id, String title, OpenScope openScope, boolean isCompleted) {
        this.id = id;
        this.title = title;
        this.openScope = openScope;
        this.isCompleted = isCompleted;
    }
}
