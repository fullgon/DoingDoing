package xyz.parkh.doing.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.parkh.doing.domain.model.friend.FriendshipState;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friendship {

    @Id
    @GeneratedValue
    @Column(name = "FRIENDSHIP_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "REQUESTER_ID", referencedColumnName = "USER_ID")
    private User requester;

    @ManyToOne
    @JoinColumn(name = "TARGET_ID", referencedColumnName = "USER_ID")
    private User target;

    @Enumerated(value = EnumType.STRING)
    private FriendshipState friendshipState;

    public Friendship(User requester, User target, FriendshipState friendshipState) {
        this.requester = requester;
        this.target = target;
        this.friendshipState = friendshipState;
    }

}
