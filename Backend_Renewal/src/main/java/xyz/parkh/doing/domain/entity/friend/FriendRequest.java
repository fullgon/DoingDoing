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
    @Column(name = "FRIEND_REQUEST_NO")
    private Long no;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "REQUESTER_ID", referencedColumnName = "USER_NO")
    private User requester;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "TARGET_ID", referencedColumnName = "USER_NO")
    private User target;

    @Enumerated(value = EnumType.STRING)
    private FriendshipState friendshipState;

    public FriendRequest(User requester, User target, FriendshipState friendshipState) {
        this.requester = requester;
        this.target = target;
        this.friendshipState = friendshipState;
    }

    public void setFriendshipState(FriendshipState friendshipState) {
        this.friendshipState = friendshipState;
    }
}
