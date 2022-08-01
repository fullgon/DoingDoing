package xyz.parkh.doing.domain.model.schedule;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    public Period(Long year, Long month, Long week, Long day, PeriodType periodType) {
        this.year = year;
        this.month = month;
        this.week = week;
        this.day = day;
        this.periodType = periodType;
    }

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
