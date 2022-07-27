package xyz.parkh.doing.domain.entity.schedule;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ToDoSchedule extends Schedule {
    private Boolean isCompleted;

    @Builder
    public ToDoSchedule(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
