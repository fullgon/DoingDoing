package xyz.parkh.doing.domain.schedule.model;

import lombok.Getter;

@Getter
public class AllCategorizedScheduleList {
    CategorizedScheduleList monthly = new CategorizedScheduleList();
    CategorizedScheduleList weekly = new CategorizedScheduleList();
    CategorizedScheduleList daily = new CategorizedScheduleList();

    public AllCategorizedScheduleList() {
    }

    public AllCategorizedScheduleList(CategorizedScheduleList monthly, CategorizedScheduleList weekly, CategorizedScheduleList daily) {
        this.monthly = monthly;
        this.weekly = weekly;
        this.daily = daily;
    }
}
