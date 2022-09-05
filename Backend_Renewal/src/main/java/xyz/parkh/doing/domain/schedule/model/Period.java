package xyz.parkh.doing.domain.schedule.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Objects;

@ToString
@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Period {
    @Column(name = "YEARS")
    private int year;

    @Column(name = "MONTHS")
    private int month;

    @Column(name = "WEEKS")
    private int week;

    @Column(name = "DAYS")
    private int day;

    @Enumerated(value = EnumType.STRING)
    private PeriodType periodType;

    @Builder
    public Period(int year, int month, int week, int day, PeriodType periodType) {
        this.year = year;
        this.month = month;
        this.week = week;
        this.day = day;
        this.periodType = periodType;
    }

    public static Period createMonthlyPeriod(int year, int month) {
        return Period.builder().year(year).month(month)
                .periodType(PeriodType.MONTH).build();
    }

    public static Period createWeeklyPeriod(int year, int month, int week) {
        return Period.builder().year(year).month(month).week(week)
                .periodType(PeriodType.WEEK).build();
    }

    public static Period createDailyPeriod(int year, int month, int day) {
        return Period.builder().year(year).month(month).day(day)
                .periodType(PeriodType.DAY).build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return year == period.year && month == period.month && week == period.week && day == period.day && periodType == period.periodType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, week, day, periodType);
    }
}
