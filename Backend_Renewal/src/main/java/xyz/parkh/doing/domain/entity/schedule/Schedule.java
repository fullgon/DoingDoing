package xyz.parkh.doing.domain.entity.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import xyz.parkh.doing.domain.entity.user.User;
import xyz.parkh.doing.domain.model.schedule.OpenScope;
import xyz.parkh.doing.domain.model.schedule.Period;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "SCHEDULE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn()
@ToString
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
}
