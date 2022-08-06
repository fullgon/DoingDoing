package xyz.parkh.doing.domain.entity.friend;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friendship {

    @Id
    @GeneratedValue
    private Long id;

    private Long id1;

    private Long id2;

    public Friendship(Long id1, Long id2) {
        this.id1 = id1;
        this.id2 = id2;
    }

    public static List<Friendship> addFriendShip(Long id1, Long id2) {
        List friendShipList = new ArrayList<>();
        friendShipList.add(new Friendship(id1, id2));
        friendShipList.add(new Friendship(id2, id1));

        return friendShipList;
    }
}
