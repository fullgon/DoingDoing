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

    public void requestFriendApplication(User requester, User target) {
        if (isFriend(requester, target)) {
            throw new NullPointerException("이미 친구");
        }

        if (hasRequestFriendApplication(requester, target)) {
            throw new NullPointerException("이미 존재하는 요청");
        }

        if (hasRequestFriendApplication(requester, target)) {
            throw new NullPointerException("이미 상대방이 요청 함");
        }

        FriendApplication friendApplication = new FriendApplication(requester, target, FriendshipState.REQUEST);
        friendRequestRepository.save(friendApplication);
    }


    public void requestCancelFriendApplication(User requester, User target) {
        FriendApplication friendApplication = getFriedApplication(requester, target);
        if (friendApplication.getFriendshipState() != FriendshipState.REQUEST) {
            throw new NullPointerException("진행 중인 친구 요청이 없습니다.");
        }
        friendApplication.setFriendshipState(FriendshipState.CANCEL);
    }

    public void responseAccept(Long friendApplicationId) {
        responseFriendApplication(friendApplicationId, FriendshipState.ACCEPT);
    }

    public void responseDecline(Long friendApplicationId) {
        responseFriendApplication(friendApplicationId, FriendshipState.DECLINE);
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

    public boolean isFriend(User user1, User user2) {
        Friendship friendship1 = friendshipRepository.findByUser1AndUser2(user1, user2);
        Friendship friendship2 = friendshipRepository.findByUser1AndUser2(user2, user1);
        if (friendship1 == null && friendship2 == null) {
            return false;
        }
        return true;
    }

    public boolean hasRequestFriendApplication(User requester, User target) {
        FriendApplication sendFriendApplication = friendRequestRepository.findByRequesterAndTargetAndFriendshipState(requester, target, FriendshipState.REQUEST);
        if (sendFriendApplication == null) {
            return false;
        }
        return true;
    }

    public List<FriendInfo> getReceiveFriendApplicationList(User user) {
        List<FriendApplication> findFriendApplicationList = friendRequestRepository.findAllByTargetAndFriendshipState(user, FriendshipState.REQUEST);

        List<FriendInfo> friendInfoList = new ArrayList<>();
        for (FriendApplication friendApplication : findFriendApplicationList) {
            User requester = friendApplication.getRequester();
            friendInfoList.add(new FriendInfo(friendApplication.getId(), requester.getAuthId(), requester.getName()));
        }

        return friendInfoList;
    }

    public FriendInfo getReceiveFriendApplication(User requester, User target, FriendshipState friendshipState) {
        FriendApplication friendApplication = friendRequestRepository.findByRequesterAndTargetAndFriendshipState(requester, target, friendshipState);

        return new FriendInfo(friendApplication.getId(), requester.getAuthId(), requester.getName());
    }

    public List<FriendInfo> getSendFriendApplicationList(User user) {
        // TODO 최근 거절된 요청도 확인 가능 하도록.
        List<FriendApplication> findFriendApplicationList = friendRequestRepository.findAllByRequesterAndFriendshipState(user, FriendshipState.REQUEST);

        List<FriendInfo> friendInfoList = new ArrayList<>();
        for (FriendApplication friendApplication : findFriendApplicationList) {
            User targetUser = friendApplication.getTarget();
            friendInfoList.add(new FriendInfo(friendApplication.getId(), targetUser.getAuthId(), targetUser.getName()));
        }
        return friendInfoList;
    }

    public void deleteFriend(User user1, User user2) {
        Friendship friendship1 = friendshipRepository.findByUser1AndUser2(user1, user2);
        Friendship friendship2 = friendshipRepository.findByUser1AndUser2(user2, user1);

        friendshipRepository.delete(friendship1);
        friendshipRepository.delete(friendship2);
    }

    public List<User> getFriendList(User user) {
        List<User> friendList = friendshipRepository.getFriendList(user);
        return friendList;
    }

    public FriendApplication getFriedApplication(User requester, User target) {
        return friendRequestRepository.findTopByRequesterAndTargetOrderByIdDesc(requester, target);
    }
}
