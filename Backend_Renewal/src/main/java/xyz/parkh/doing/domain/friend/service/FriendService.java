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

    public void requestFriendApplication(String requesterAuthId, String targetAuthId) {
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

    public List<FriendInfo> getReceiveFriendApplicationList(String authId) {
        User targetUser = userService.findByAuthId(authId);
        List<FriendApplication> findFriendApplicationList = friendRequestRepository.findAllByTargetAndFriendshipState(targetUser, FriendshipState.REQUEST);

        List<FriendInfo> friendInfoList = new ArrayList<>();
        for (FriendApplication friendApplication : findFriendApplicationList) {
            User requester = friendApplication.getRequester();
            friendInfoList.add(new FriendInfo(friendApplication.getId(), requester.getAuthId(), requester.getName()));
        }

        return friendInfoList;
    }

    public List<FriendInfo> getSendFriendApplicationList(String authId) {
        List<FriendApplication> findFriendApplicationList = friendRequestRepository.findAllByRequester_AuthIdAndFriendshipState(authId, FriendshipState.REQUEST);

        List<FriendInfo> friendInfoList = new ArrayList<>();
        for (FriendApplication friendApplication : findFriendApplicationList) {
            User targetUser = friendApplication.getTarget();
            friendInfoList.add(new FriendInfo(friendApplication.getId(), targetUser.getAuthId(), targetUser.getName()));
        }
        return friendInfoList;
    }

    // 친구 신청 - 수락, 거절

    // 친구 요청 목록 확인

    // 친구 목록 확인


}
