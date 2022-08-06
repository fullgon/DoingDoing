package xyz.parkh.doing.domain.entity.friend;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xyz.parkh.doing.domain.entity.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friendship {

    @Id
    @GeneratedValue
    @Column(name = "FRIENDSHIP_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "USER1")
    private User user1;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "USER2")
    private User user2;

    public Friendship(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public static List<Friendship> addFriendShip(User user1, User user2) {
        List friendShipList = new ArrayList<>();
        friendShipList.add(new Friendship(user1, user2));
        friendShipList.add(new Friendship(user2, user1));

        return friendShipList;
    }
}
