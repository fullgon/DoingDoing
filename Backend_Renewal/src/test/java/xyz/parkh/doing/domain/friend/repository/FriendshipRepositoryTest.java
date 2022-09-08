package xyz.parkh.doing.domain.friend.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.friend.entity.Friendship;
import xyz.parkh.doing.domain.friend.model.FriendInfo;
import xyz.parkh.doing.domain.friend.service.FriendService;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.user.repository.UserRepository;
import xyz.parkh.doing.domain.user.service.UserService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class FriendshipRepositoryTest {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Before
    public void 친구_요청(){
        User userA = User.builder().name("userAName").authId("userAID").build();
        User userB = User.builder().name("userBName").authId("userBID").build();
        User userC = User.builder().name("userCName").authId("userCID").build();
        User userD = User.builder().name("userDName").authId("userDID").build();

        userRepository.save(userA);
        userRepository.save(userB);
        userRepository.save(userC);
        userRepository.save(userD);

        friendService.requestFriendApplication(userA.getAuthId(), userB.getAuthId());
        friendService.requestFriendApplication(userA.getAuthId(), userC.getAuthId());
        friendService.requestFriendApplication(userD.getAuthId(), userA.getAuthId());
    }

    @Test
    public void 친구_목록_조회() {
        User user = userRepository.findByAuthId("userAID");
        List<FriendInfo> friendInfoList = friendService.getReceiveFriendApplicationList("userAID");
        for (FriendInfo friendInfo : friendInfoList) {
            friendService.responseAccept(friendInfo.getId());
        }

        List<User> friendList = friendshipRepository.getFriendList(user);
    }
}