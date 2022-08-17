package xyz.parkh.doing.domain.friend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.friend.entity.FriendRequest;
import xyz.parkh.doing.domain.friend.entity.Friendship;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.friend.model.FriendshipState;
import xyz.parkh.doing.domain.friend.repository.FriendRequestRepository;
import xyz.parkh.doing.domain.friend.repository.FriendshipRepository;
import xyz.parkh.doing.domain.user.service.UserService;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FriendService {

    private final FriendRequestRepository friendRequestRepository;
    private final FriendshipRepository friendshipRepository;
    private final UserService userService;

    // 친구 신청 - 요청
    public void requestFriend(String requesterAuthId, String targetAuthId) {
        User requester = userService.findByAuthId(requesterAuthId);
        User target = userService.findByAuthId(targetAuthId);

        FriendRequest friendRequest = new FriendRequest(requester, target, FriendshipState.REQUEST);
        friendRequestRepository.save(friendRequest);
    }

    public List<FriendRequest> getFriendRequestList(String authId) {
        User targetUser = userService.findByAuthId(authId);
        List<FriendRequest> findFriendshipRequestList = friendRequestRepository.findAllByTargetAndFriendshipState(targetUser, FriendshipState.REQUEST);
        return findFriendshipRequestList;
    }

    public void responseFriendRequest(Long friendRequestId, FriendshipState friendshipState) {
        FriendRequest friendRequest = friendRequestRepository.findById(friendRequestId).get();
        friendRequest.setFriendshipState(friendshipState);

        if (friendshipState == FriendshipState.ACCEPT) {
            User requester = friendRequest.getRequester();
            User target = friendRequest.getTarget();
            List<Friendship> friendships = Friendship.addFriendShip(requester, target);
            friendshipRepository.saveAll(friendships);
        }
    }

    // 친구 신청 - 수락, 거절

    // 친구 요청 목록 확인

    // 친구 목록 확인


}
