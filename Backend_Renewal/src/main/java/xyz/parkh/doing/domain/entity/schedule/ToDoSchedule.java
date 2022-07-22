package xyz.parkh.doing.domain.entity.schedule;

import javax.persistence.Entity;

@Entity
public class ToDoSchedule extends Schedule {
    private Boolean isCompleted;
}
