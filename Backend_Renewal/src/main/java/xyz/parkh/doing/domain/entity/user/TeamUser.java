package xyz.parkh.doing.domain.entity.user;

import xyz.parkh.doing.domain.model.user.MemberType;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
public class TeamUser {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Enumerated(value = EnumType.STRING)
    private MemberType type;
}
