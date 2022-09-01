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

    @Column(name = "CREATE_DATE")
    private LocalDate createDate;

    @Enumerated(value = EnumType.STRING)
    private PeriodType periodType;

    private Period(int year, int month, int week, PeriodType periodType) {
        this.year = year;
        this.month = month;
        this.week = week;
        this.periodType = periodType;
        this.createDate = LocalDate.now();
    }

    public static Period makeTodayPeriod(PeriodType periodType) {
        LocalDate now = LocalDate.now();
        int dayOfWeek = now.getDayOfWeek().getValue();
        LocalDate thursdayOfDate = now.minusDays(dayOfWeek - 4);

        LocalDate firstThursdayOfDate = null;
        for (int i = 1; i <= 7; i++) {
            LocalDate sequentialDate = thursdayOfDate.withDayOfMonth(i);
            if (sequentialDate.getDayOfWeek() == DayOfWeek.THURSDAY) {
                firstThursdayOfDate = sequentialDate;
                break;
            }
        }

        int year = thursdayOfDate.getYear();
        int month = thursdayOfDate.getMonth().getValue();
        int week = (thursdayOfDate.getDayOfMonth() - firstThursdayOfDate.getDayOfMonth()) / 7 + 1;

        return new Period(year, month, week, periodType);
    }

    public static Period makePeriod(LocalDate date, PeriodType periodType) {
        int dayOfWeek = date.getDayOfWeek().getValue();
        LocalDate thursdayOfDate = date.minusDays(dayOfWeek - 4);

        LocalDate firstThursdayOfDate = null;
        for (int i = 1; i <= 7; i++) {
            LocalDate sequentialDate = thursdayOfDate.withDayOfMonth(i);
            if (sequentialDate.getDayOfWeek() == DayOfWeek.THURSDAY) {
                firstThursdayOfDate = sequentialDate;
                break;
            }
        }

        int year = thursdayOfDate.getYear();
        int month = thursdayOfDate.getMonth().getValue();
        int week = (thursdayOfDate.getDayOfMonth() - firstThursdayOfDate.getDayOfMonth()) / 7 + 1;

        return new Period(year, month, week, periodType);
    }

    public static Period createTodayMonthlyPeriod() {
        return makeTodayPeriod(PeriodType.MONTH);
    }

    public static Period createTodayWeeklyPeriod() {
        return makeTodayPeriod(PeriodType.WEEK);
    }

    public static Period createTodayDailyPeriod() {
        return makeTodayPeriod(PeriodType.DAY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return year == period.year && month == period.month && week == period.week
                && Objects.equals(createDate, period.createDate) && periodType == period.periodType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, week, createDate, periodType);
    }
}
