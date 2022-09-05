package xyz.parkh.doing.domain.schedule.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CategorizedScheduleList {
    List<ShortHabitSchedule> habitScheduleList = new ArrayList<>();
    List<ShortToDoSchedule> toDoScheduleList = new ArrayList<>();

    public CategorizedScheduleList(List<ShortHabitSchedule> habitScheduleList, List<ShortToDoSchedule> toDoScheduleList) {
        this.habitScheduleList = habitScheduleList;
        this.toDoScheduleList = toDoScheduleList;
    }
}
