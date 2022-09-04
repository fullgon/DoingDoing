package xyz.parkh.doing.domain.schedule.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.experimental.categories.Categories;
import xyz.parkh.doing.domain.schedule.model.ScheduleType;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.schedule.model.OpenScope;
import xyz.parkh.doing.domain.schedule.model.Period;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "SCHEDULE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "SCHEDULE_TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class Schedule {

    @Id
    @GeneratedValue
    @Column(name = "SCHEDULE_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "USERS_ID")
    private User user;

    private String title;

    @Enumerated(value = EnumType.STRING)
    private OpenScope openScope;

    @Embedded
    private Period period;

    // DiscriminatorColumn
//    @Column(name = "SCHEDULE_TYPE", insertable = false, updatable = false)
//    private String scheduleType;

    @Enumerated(value = EnumType.STRING)
    private ScheduleType scheduleType;

    public Schedule(User user, String title, OpenScope openScope, Period period, ScheduleType scheduleType) {
        this.user = user;
        this.title = title;
        this.openScope = openScope;
        this.period = period;
        this.scheduleType = scheduleType;
    }

    @Override
    public boolean equals(Object o) {
        // TODO LAZY 로딩으로 프록시 객체 조회 되어 비교에서 제외, 제대로 된 해결 방법 찾기
        // TODO scheduleType 읽기 전용으로 추가
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(id, schedule.id) && Objects.equals(title, schedule.title) && openScope == schedule.openScope && Objects.equals(period, schedule.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, openScope, period);
    }
}
