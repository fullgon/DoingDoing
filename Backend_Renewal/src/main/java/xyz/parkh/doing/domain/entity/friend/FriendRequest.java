package xyz.parkh.doing.domain.entity.friend;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.parkh.doing.domain.entity.user.User;
import xyz.parkh.doing.domain.model.friend.FriendshipState;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FriendRequest {

    @Id
    @GeneratedValue
    @Column(name = "FRIENDSHIP_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "REQUESTER_ID", referencedColumnName = "USER_ID")
    private User requester;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "TARGET_ID", referencedColumnName = "USER_ID")
    private User target;

    @Enumerated(value = EnumType.STRING)
    private FriendshipState friendshipState;

    public FriendRequest(User requester, User target, FriendshipState friendshipState) {
        this.requester = requester;
        this.target = target;
        this.friendshipState = friendshipState;
    }

}
