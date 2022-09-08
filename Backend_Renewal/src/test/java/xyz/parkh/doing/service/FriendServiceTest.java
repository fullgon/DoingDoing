package xyz.parkh.doing.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.friend.model.FriendInfo;
import xyz.parkh.doing.domain.friend.repository.FriendRequestRepository;
import xyz.parkh.doing.domain.friend.service.FriendService;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.user.repository.UserRepository;
import xyz.parkh.doing.domain.user.service.UserService;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    // TODO 친구 목록 확인

    @Test
    public void 친구_요청_취소() {
        List<FriendInfo> friendInfoList = friendService.getSendFriendApplicationList("userDID");

        for (FriendInfo friendInfo : friendInfoList) {
            friendService.requestCancelFriendApplication(friendInfo.getId());
        }

        friendInfoList = friendService.getSendFriendApplicationList("userDID");
        assertEquals(friendInfoList.size(), 0);
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


    // 친구 신청 응답
    @Test
    public void 친구_요청_수락() {
        List<FriendInfo> friendInfoList = friendService.getReceiveFriendApplicationList("userAID");
        assertEquals(friendInfoList.size(), 1);

        for (FriendInfo friendInfo : friendInfoList) {
            friendService.responseAccept(friendInfo.getId());
        }

        Boolean isFriend = friendService.isFriend("userAID", "userDID");
        assertTrue(isFriend);
    }

    @Test
    public void 친구_요청_거절() {
        List<FriendInfo> friendInfoList = friendService.getReceiveFriendApplicationList("userAID");
        assertEquals(friendInfoList.size(), 1);

        for (FriendInfo friendInfo : friendInfoList) {
            friendService.responseDecline(friendInfo.getId());
        }

        Boolean isFriend = friendService.isFriend("userAID", "userDID");
        assertTrue(!isFriend);
    }

    @Test
    public void 친구_삭제() {
        // given
        List<FriendInfo> friendInfoList = friendService.getReceiveFriendApplicationList("userAID");
        for (FriendInfo friendInfo : friendInfoList) {
            friendService.responseAccept(friendInfo.getId());
        }
        Boolean isFriend = friendService.isFriend("userAID", "userDID");
        assertTrue(isFriend);

        // when
        friendService.deleteFriend("userAID", "userDID");

        // then
        Boolean isFriendAfterDelete = friendService.isFriend("userAID", "userDID");
        assertTrue(!isFriendAfterDelete);
    }

    @Test
    public void 친구_목록_조회() {
        List<FriendInfo> friendInfoList = friendService.getReceiveFriendApplicationList("userAID");
        for (FriendInfo friendInfo : friendInfoList) {
            friendService.responseAccept(friendInfo.getId());
        }
        List<User> friendList = friendService.getFriendList("userAID");
        assertEquals(friendList.size(), 1);
    }
}