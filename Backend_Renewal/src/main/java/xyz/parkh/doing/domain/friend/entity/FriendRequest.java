package xyz.parkh.doing.domain.friend.entity;

import lombok.*;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.friend.model.FriendshipState;

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
    @Column(name = "FRIEND_REQUEST_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "REQUESTER_ID")
    private User requester;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "TARGET_ID")
    private User target;

    @Enumerated(value = EnumType.STRING)
    private FriendshipState friendshipState;

    @Builder
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
        return Objects.equals(id, that.id) && Objects.equals(requester, that.requester)
                && Objects.equals(target, that.target) && friendshipState == that.friendshipState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requester, target, friendshipState);
    }
}
