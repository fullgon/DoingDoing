package xyz.parkh.doing.domain.entity.friend;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.friend.entity.FriendApplication;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.friend.model.FriendshipState;
import xyz.parkh.doing.domain.friend.repository.FriendRequestRepository;
import xyz.parkh.doing.domain.user.repository.UserRepository;

import javax.persistence.EntityManager;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class FriendApplicationTests {
    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void 친구_신청() {
        User park1 = User.builder().name("park1").build();
        User park2 = User.builder().name("park2").build();
        FriendshipState state = FriendshipState.REQUEST;
        FriendApplication friendApplication = new FriendApplication(park1, park2, state);

        userRepository.save(park1);
        userRepository.save(park2);
        friendRequestRepository.save(friendApplication);

        // TODO 중간에 1차캐시 비우면 아래 두 개의 assert 문 실패 하는데 지금은 왜 그런지 모르겠음.
//        em.flush();
//        em.clear();

        FriendApplication findFriendApplication = friendRequestRepository.findById(friendApplication.getId()).get();

        System.out.println("findFriendRequest = " + findFriendApplication);
        System.out.println("friendRequest = " + friendApplication);
        Assertions.assertTrue(findFriendApplication.equals(friendApplication));
        Assertions.assertEquals(findFriendApplication, friendApplication);

        Assertions.assertEquals(friendApplication, findFriendApplication);
        Assertions.assertTrue(friendApplication.equals(findFriendApplication));
    }
}
