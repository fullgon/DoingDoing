package xyz.parkh.doing.domain.entity.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.parkh.doing.domain.entity.User;
import xyz.parkh.doing.domain.model.schedule.OpenScope;
import xyz.parkh.doing.domain.model.schedule.Period;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "SCHEDULE")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn()
public abstract class Schedule {

    @Id
    @GeneratedValue
    @Column(name = "schedule_no")
    private Long no;

    @ManyToOne
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
