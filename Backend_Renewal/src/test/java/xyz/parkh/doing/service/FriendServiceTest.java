package xyz.parkh.doing.service;

import org.junit.Test;
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

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class FriendServiceTest {

    @Autowired
    private FriendService friendService;

    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void 친구_요청_후_조회() {
        User userA = User.builder().name("userAName").authId("userAID").build();
        User userB = User.builder().name("userBName").authId("userBID").build();
        User userC = User.builder().name("userCName").authId("userCID").build();
        userRepository.save(userA);
        userRepository.save(userB);
        userRepository.save(userC);

        friendService.requestFriend(userA.getAuthId(), userB.getAuthId());
        friendService.requestFriend(userA.getAuthId(), userC.getAuthId());
        friendService.requestFriend(userB.getAuthId(), userA.getAuthId());
        friendService.requestFriend(userC.getAuthId(), userA.getAuthId());

        List<FriendRequest> friendRequestList = friendService.getFriendRequestList(userA.getAuthId());
        assertEquals(friendRequestList.size(), 2);
    }

    @Test
    @Rollback(value = false)
    public void 친구_요청_수락() {
        User userA = User.builder().name("userAName").authId("userAID").build();
        User userB = User.builder().name("userBName").authId("userBID").build();
        userRepository.save(userA);
        userRepository.save(userB);
        friendService.requestFriend(userA.getAuthId(), userB.getAuthId());

        List<FriendRequest> friendRequestListBeforeAccept = friendRequestRepository.findAllByRequesterAndTarget(userA, userB);
        for (FriendRequest friendRequest : friendRequestListBeforeAccept) {
            if (friendRequest.getFriendshipState().equals(FriendshipState.REQUEST)) {
                friendService.responseFriendRequest(friendRequest.getId(), FriendshipState.ACCEPT);
            }
        }

        boolean findState = false;
        List<FriendRequest> friendRequestListAfterAccept = friendRequestRepository.findAllByRequesterAndTarget(userA, userB);
        for (FriendRequest friendRequest : friendRequestListAfterAccept) {
            if (friendRequest.getFriendshipState().equals(FriendshipState.ACCEPT)) {
                findState = true;
            }
        }

        if (!findState) {
            throw new AssertionError();
        }
    }
}