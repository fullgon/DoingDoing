package xyz.parkh.doing.domain.schedule.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.parkh.doing.domain.schedule.model.OpenScope;
import xyz.parkh.doing.domain.schedule.model.Period;
import xyz.parkh.doing.domain.schedule.model.ScheduleChangeDto;
import xyz.parkh.doing.domain.schedule.model.ScheduleType;
import xyz.parkh.doing.domain.user.entity.User;

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
        if (this == o) return true;
        if (!(o instanceof Schedule)) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(id, schedule.getId())
                && Objects.equals(title, schedule.getTitle())
                && Objects.equals(openScope, schedule.getOpenScope())
                && Objects.equals(period, schedule.openScope);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, openScope, period);
    }

    public void updateSchedule(ScheduleChangeDto scheduleChangeDto) {
        if (scheduleChangeDto.getScheduleType() != null) {
            this.scheduleType = scheduleChangeDto.getScheduleType();
        }
        if (scheduleChangeDto.getOpenScope() != null) {
            this.openScope = scheduleChangeDto.getOpenScope();
        }
        if (scheduleChangeDto.getTitle() != null) {
            this.title = scheduleChangeDto.getTitle();
        }
        if (scheduleChangeDto.getPeriod() != null) {
            this.period = scheduleChangeDto.getPeriod();
        }
    }
}
