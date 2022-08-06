package xyz.parkh.doing.domain.entity.user;

import xyz.parkh.doing.domain.model.user.MemberType;

import javax.persistence.*;

@Entity
public class TeamUser {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private MemberType type;
}
