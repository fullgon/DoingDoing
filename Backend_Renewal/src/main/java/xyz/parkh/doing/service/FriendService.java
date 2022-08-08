package xyz.parkh.doing.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.entity.friend.FriendRequest;
import xyz.parkh.doing.domain.entity.friend.Friendship;
import xyz.parkh.doing.domain.entity.user.User;
import xyz.parkh.doing.domain.model.friend.FriendshipState;
import xyz.parkh.doing.repository.FriendRequestRepository;
import xyz.parkh.doing.repository.FriendshipRepository;
import xyz.parkh.doing.repository.UserRepository;

import java.util.List;
import java.util.Optional;

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
