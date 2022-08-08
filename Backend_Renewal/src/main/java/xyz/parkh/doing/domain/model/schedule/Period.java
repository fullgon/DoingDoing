package xyz.parkh.doing.domain.model.schedule;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

@Embeddable
@Getter
@NoArgsConstructor
public class Period {
    @Column(name = "YEARS")
    private Long year;

    @Column(name = "MONTHS")
    private Long month;

    @Column(name = "WEEKS")
    private Long week;

    @Column(name = "DAYS")
    private Long day;

    @Enumerated(value = EnumType.STRING)
    private PeriodType periodType;

    @Builder
    private Period(Long year, Long month, Long week, Long day, PeriodType periodType) {
        this.year = year;
        this.month = month;
        this.week = week;
        this.day = day;
        this.periodType = periodType;
    }

//    public static Period createMonthPeriod() {
//         TODO 주차 구하는 로직 추가
//        LocalDate now = LocalDate.now();
//        Period period = Period.builder()
//                .year(year).month(month).periodType(PeriodType.MONTH)
//                .build();
//        return period;
//    }

//    public static Period createWeekPeriod() {
//         TODO 주차 구하는 로직 추가
//        LocalDate now = LocalDate.now();
//        Period period = Period.builder()
//                .year(year).month(month).week(week)
//                .periodType(PeriodType.WEEK)
//                .build();
//        return period;
//    }

//    public static Period createDayPeriod(Long year, Long month, Long day) {
//         TODO 주차 구하는 로직 추가
//        LocalDate now = LocalDate.now();
//
//        Period period = Period.builder()
//                .year(year).month(month).day(day)
//                .periodType(PeriodType.DAY)
//                .build();
//        return period;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Period period = (Period) o;

        if (year != null ? !year.equals(period.year) : period.year != null) return false;
        if (month != null ? !month.equals(period.month) : period.month != null) return false;
        if (week != null ? !week.equals(period.week) : period.week != null) return false;
        if (day != null ? !day.equals(period.day) : period.day != null) return false;
        return periodType == period.periodType;
    }

    @Override
    public int hashCode() {
        int result = year != null ? year.hashCode() : 0;
        result = 31 * result + (month != null ? month.hashCode() : 0);
        result = 31 * result + (week != null ? week.hashCode() : 0);
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + (periodType != null ? periodType.hashCode() : 0);
        return result;
    }
}
