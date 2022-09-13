package xyz.parkh.doing.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.friend.entity.FriendApplication;
import xyz.parkh.doing.domain.friend.model.FriendInfo;
import xyz.parkh.doing.domain.friend.model.FriendshipState;
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

    User userA;
    User userB;
    User userC;
    User userD;

    @Before
    public void 친구_요청() {
        userA = User.builder().name("userAName").authId("userAID").build();
        userB = User.builder().name("userBName").authId("userBID").build();
        userC = User.builder().name("userCName").authId("userCID").build();
        userD = User.builder().name("userDName").authId("userDID").build();

        userRepository.save(userA);
        userRepository.save(userB);
        userRepository.save(userC);
        userRepository.save(userD);

        friendService.requestFriendApplication(userA, userB);
        friendService.requestFriendApplication(userA, userC);
        friendService.requestFriendApplication(userD, userA);
    }

    // TODO 친구 목록 확인

    @Test
    public void 친구_요청_취소() {
        FriendApplication friendApplication = friendService.getFriedApplication(userD, userA);
        assertEquals(FriendshipState.REQUEST, friendApplication.getFriendshipState());

        friendService.requestCancelFriendApplication(userD, userA);
        friendApplication = friendService.getFriedApplication(userD, userA);
        assertEquals(FriendshipState.CANCEL, friendApplication.getFriendshipState());
    }


    @Test
    public void 사용자에게_친구_요청_보낸_사용자_목록_조회() {
        List<FriendInfo> friendInfoList = friendService.getReceiveFriendApplicationList(userB);

        assertEquals(1, friendInfoList.size());
    }

    @Test
    public void 사용자가_친구_요청_보낸_사용자_목록_조회() {
        List<FriendInfo> friendInfoList = friendService.getSendFriendApplicationList(userA);

        assertEquals(2, friendInfoList.size());
    }


    // 친구 신청 응답
    @Test
    public void 친구_요청_수락() {
        List<FriendInfo> friendInfoList = friendService.getReceiveFriendApplicationList(userA);
        assertEquals(friendInfoList.size(), 1);

        for (FriendInfo friendInfo : friendInfoList) {
            friendService.responseAccept(friendInfo.getId());
        }

        Boolean isFriend = friendService.isFriend(userA, userD);
        assertTrue(isFriend);
    }

    @Test
    public void 친구_요청_거절() {
        List<FriendInfo> friendInfoList = friendService.getReceiveFriendApplicationList(userA);
        assertEquals(friendInfoList.size(), 1);

        for (FriendInfo friendInfo : friendInfoList) {
            friendService.responseDecline(friendInfo.getId());
        }

        Boolean isFriend = friendService.isFriend(userA, userD);
        assertTrue(!isFriend);
    }

    @Test
    public void 친구_삭제() {
        // given
        List<FriendInfo> friendInfoList = friendService.getReceiveFriendApplicationList(userA);
        for (FriendInfo friendInfo : friendInfoList) {
            friendService.responseAccept(friendInfo.getId());
        }
        Boolean isFriend = friendService.isFriend(userA, userD);
        assertTrue(isFriend);

        // when
        friendService.deleteFriend(userA, userD);

        // then
        Boolean isFriendAfterDelete = friendService.isFriend(userA, userD);
        assertTrue(!isFriendAfterDelete);
    }

    @Test
    public void 친구_목록_조회() {
        List<FriendInfo> friendInfoList = friendService.getReceiveFriendApplicationList(userA);
        for (FriendInfo friendInfo : friendInfoList) {
            friendService.responseAccept(friendInfo.getId());
        }
        List<User> friendList = friendService.getFriendList(userA);
        assertEquals(friendList.size(), 1);
    }

    @Test
    public void 최근_친구_요청_단건_조회() {
        FriendApplication firstFriedApplication = friendService.getFriedApplication(userA, userB);
        friendService.responseDecline(firstFriedApplication.getId());

        friendService.requestFriendApplication(userA, userB);
        FriendApplication secondFriedApplication = friendService.getFriedApplication(userA, userB);
        Assertions.assertTrue(firstFriedApplication.getId() != secondFriedApplication.getId());
    }
}
