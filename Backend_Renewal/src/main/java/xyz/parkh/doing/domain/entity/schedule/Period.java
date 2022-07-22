package xyz.parkh.doing.domain.entity.schedule;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

@Embeddable
public class Period {
    @Column(name = "YEARS")
    private Long year;

    @Column(name = "MONTHS")
    private Long month;

    @Column(name = "WEEKS")
    private Long week;

    @Column(name = "DAYS")
    private Long day;

    @Enumerated
    private PeriodType periodType;

}
