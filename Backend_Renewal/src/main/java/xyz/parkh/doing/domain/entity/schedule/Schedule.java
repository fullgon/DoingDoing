package xyz.parkh.doing.domain.entity.schedule;

import lombok.*;
import xyz.parkh.doing.domain.entity.user.User;
import xyz.parkh.doing.domain.model.schedule.OpenScope;
import xyz.parkh.doing.domain.model.schedule.Period;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "SCHEDULE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Schedule {

    @Id
    @GeneratedValue
    @Column(name = "schedule_no")
    private Long no;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    private String title;

    @Lob
    private String content;

    @Enumerated(value = EnumType.STRING)
    private OpenScope openScope;

    @Embedded
    private Period period;

    public Schedule(User user, String title, String content, OpenScope openScope, Period period) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.openScope = openScope;
        this.period = period;
    }

    @Override
    public boolean equals(Object o) {
        // TODO LAZY 로딩으로 프록시 객체 조회 되어 비교에서 제외, 제대로 된 해결 방법 찾기
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(no, schedule.no) && Objects.equals(title, schedule.title) && Objects.equals(content, schedule.content) && openScope == schedule.openScope && Objects.equals(period, schedule.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, title, content, openScope, period);
    }
}
