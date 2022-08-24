package xyz.parkh.doing.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.friend.model.FriendInfo;
import xyz.parkh.doing.domain.friend.service.FriendService;
import xyz.parkh.doing.domain.friend.entity.FriendApplication;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.friend.model.FriendshipState;
import xyz.parkh.doing.domain.friend.repository.FriendRequestRepository;
import xyz.parkh.doing.domain.user.service.UserService;
import xyz.parkh.doing.domain.user.repository.UserRepository;

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

    @Before
    public void 친구_요청() {
        User userA = User.builder().name("userAName").authId("userAID").build();
        User userB = User.builder().name("userBName").authId("userBID").build();
        User userC = User.builder().name("userCName").authId("userCID").build();

        userRepository.save(userA);
        userRepository.save(userB);
        userRepository.save(userC);

        friendService.requestFriendApplication(userA.getAuthId(), userB.getAuthId());
        friendService.requestFriendApplication(userA.getAuthId(), userC.getAuthId());
        friendService.requestFriendApplication(userB.getAuthId(), userA.getAuthId());
    }

    @Test
    public void 사용자에게_친구_요청_보낸_사용자_목록_조회() {
        List<FriendInfo> friendInfoList = friendService.getReceiveFriendApplicationList("userAID");

        assertEquals(friendInfoList.size(), 1);
    }

    @Test
    public void 사용자가_친구_요청_보낸_사용자_목록_조회() {
        List<FriendInfo> friendInfoList = friendService.getSendFriendApplicationList("userAID");

        assertEquals(friendInfoList.size(), 2);
    }

    // 친구 삭제


    // 친구 신청 응답
    @Test
//    @Rollback(value = false)
    public void 친구_요청_수락() {
        List<FriendInfo> friendInfoList = friendService.getReceiveFriendApplicationList("userAID");
        assertEquals(friendInfoList.size(), 1);

        for (FriendInfo friendInfo : friendInfoList) {
            friendService.responseFriendApplication(friendInfo.getFriendApplicationId(), FriendshipState.ACCEPT);
        }

        friendInfoList = friendService.getReceiveFriendApplicationList("userAID");
        assertEquals(friendInfoList.size(), 0);
        // TODO 친구 관계인지 파악하는 메서드 추가
    }
}