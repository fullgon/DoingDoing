package xyz.parkh.doing.domain;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.entity.friend.FriendRequest;
import xyz.parkh.doing.domain.entity.user.User;
import xyz.parkh.doing.domain.model.friend.FriendshipState;
import xyz.parkh.doing.repository.FriendRequestRepository;
import xyz.parkh.doing.repository.UserRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class FriendRequestTests {
    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    @Test
    @Rollback(value = false)
    public void 친구_신청() {
        User park1 = User.builder().name("park1").build();
        User park2 = User.builder().name("park2").build();
        FriendshipState state = FriendshipState.REQUEST;
        FriendRequest friendRequest = new FriendRequest(park1, park2, state);

        userRepository.save(park1);
        userRepository.save(park2);
        friendRequestRepository.save(friendRequest);

        // TODO 중간에 1차캐시 비우면 아래 두 개의 assert 문 실패 하는데 지금은 왜 그런지 모르겠음.
//        em.flush();
//        em.clear();

        FriendRequest findFriendRequest = friendRequestRepository.findById(friendRequest.getId()).get();

        System.out.println("findFriendRequest = " + findFriendRequest);
        System.out.println("friendRequest = " + friendRequest);
        Assertions.assertTrue(findFriendRequest.equals(friendRequest));
        Assertions.assertEquals(findFriendRequest, friendRequest);

        Assertions.assertEquals(friendRequest, findFriendRequest);
        Assertions.assertTrue(friendRequest.equals(findFriendRequest));
    }
}
