package xyz.parkh.doing.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.friend.repository.FriendRequestRepository;
import xyz.parkh.doing.domain.friend.entity.FriendRequest;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.friend.model.FriendshipState;
import xyz.parkh.doing.domain.user.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class FriendRequestRepositoryTest {

    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void 친구_요청_전송() {
        User userA = User.builder().name("userAName").authId("userAID").build();
        User userB = User.builder().name("userBName").authId("userBID").build();
        FriendRequest friendRequest = FriendRequest.builder().requester(userA).target(userB)
                .friendshipState(FriendshipState.REQUEST).build();

        userRepository.save(userA);
        userRepository.save(userB);
        friendRequestRepository.save(friendRequest);

        friendRequestRepository.flush();

        FriendRequest findFriendRequest = friendRequestRepository.findById(friendRequest.getId()).get();
        assertEquals(findFriendRequest, friendRequest);
    }

}