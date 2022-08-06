package xyz.parkh.doing.domain.entity.friend;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import xyz.parkh.doing.domain.entity.user.User;
import xyz.parkh.doing.domain.model.friend.FriendshipState;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendRequest that = (FriendRequest) o;
        return Objects.equals(no, that.no) && Objects.equals(requester, that.requester)
                && Objects.equals(target, that.target) && friendshipState == that.friendshipState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, requester, target, friendshipState);
    }
}
