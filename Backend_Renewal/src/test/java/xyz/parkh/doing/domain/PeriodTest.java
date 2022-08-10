package xyz.parkh.doing.domain;

import org.junit.Assert;
import org.junit.Test;
import xyz.parkh.doing.domain.model.schedule.Period;
import xyz.parkh.doing.domain.model.schedule.PeriodType;

public class PeriodTest {
    // 의미 없어 보이지만, 추후 Period 관련 로직이 변경 되었을 경우 검증하기 위해 테스트 작성
    @Test
    public void 월간_일정() {
        Period monthPeriod = Period.createMonthPeriod();
        Assert.assertEquals(monthPeriod.getPeriodType(), PeriodType.MONTH);
    }

    @Test
    public void 주간_일정() {
        Period weekPeriod = Period.createWeekPeriod();
        Assert.assertEquals(weekPeriod.getPeriodType(), PeriodType.WEEK);
    }

    @Test
    public void 일간_일정() {
        Period dayPeriod = Period.createDayPeriod();
        Assert.assertEquals(dayPeriod.getPeriodType(), PeriodType.DAY);
    }
}
