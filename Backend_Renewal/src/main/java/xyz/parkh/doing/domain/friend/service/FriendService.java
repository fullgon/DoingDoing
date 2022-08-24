package xyz.parkh.doing.domain.friend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.friend.entity.FriendApplication;
import xyz.parkh.doing.domain.friend.entity.Friendship;
import xyz.parkh.doing.domain.friend.model.FriendInfo;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.friend.model.FriendshipState;
import xyz.parkh.doing.domain.friend.repository.FriendRequestRepository;
import xyz.parkh.doing.domain.friend.repository.FriendshipRepository;
import xyz.parkh.doing.domain.user.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FriendService {

    private final FriendRequestRepository friendRequestRepository;
    private final FriendshipRepository friendshipRepository;
    private final UserService userService;

    public void requestFriendApplication(String requesterAuthId, String targetAuthId) throws Exception {
        FriendApplication friendshipState = friendRequestRepository.findByRequester_AuthIdAndTarget_AuthIdAndFriendshipState(requesterAuthId, targetAuthId, FriendshipState.REQUEST);
        if (friendshipState != null) {
            // TODO 기존에 전송한 요청이 있을 경우 에러 메시지 전송("이미 신청한 사용자")
            throw new Exception();
        }

        User requester = userService.findByAuthId(requesterAuthId);
        User target = userService.findByAuthId(targetAuthId);

        FriendApplication friendApplication = new FriendApplication(requester, target, FriendshipState.REQUEST);
        friendRequestRepository.save(friendApplication);
    }

    public void responseFriendApplication(Long friendApplicationId, FriendshipState friendshipState) {
        FriendApplication friendApplication = friendRequestRepository.findById(friendApplicationId).get();
        friendApplication.setFriendshipState(friendshipState);

        // 친구 요청 수락 시 (친구1, 친구2) / (친구2, 친구1) DB에 중복해 저장
        if (friendshipState == FriendshipState.ACCEPT) {
            User requester = friendApplication.getRequester();
            User target = friendApplication.getTarget();
            List<Friendship> friendships = Friendship.addFriendShip(requester, target);
            friendshipRepository.saveAll(friendships);
        }
    }

    public Boolean isFriend(String authId1, String authId2) {
        Friendship friendship = friendshipRepository.findAllByUser1_AuthIdAndUser2_AuthId(authId1, authId2);
        if (friendship == null) {
            return false;
        }
        return true;
    }

    public List<FriendInfo> getReceiveFriendApplicationList(String authId) {
        List<FriendApplication> findFriendApplicationList = friendRequestRepository.findAllByTarget_AuthIdAndFriendshipState(authId, FriendshipState.REQUEST);

        List<FriendInfo> friendInfoList = new ArrayList<>();
        for (FriendApplication friendApplication : findFriendApplicationList) {
            User requester = friendApplication.getRequester();
            friendInfoList.add(new FriendInfo(friendApplication.getId(), requester.getAuthId(), requester.getName()));
        }

        return friendInfoList;
    }

    public List<FriendInfo> getSendFriendApplicationList(String authId) {
        // TODO 최근 거절된 요청도 확인 가능 하도록.
        List<FriendApplication> findFriendApplicationList = friendRequestRepository.findAllByRequester_AuthIdAndFriendshipState(authId, FriendshipState.REQUEST);

        List<FriendInfo> friendInfoList = new ArrayList<>();
        for (FriendApplication friendApplication : findFriendApplicationList) {
            User targetUser = friendApplication.getTarget();
            friendInfoList.add(new FriendInfo(friendApplication.getId(), targetUser.getAuthId(), targetUser.getName()));
        }
        return friendInfoList;
    }


    // 친구 목록 확인


}
