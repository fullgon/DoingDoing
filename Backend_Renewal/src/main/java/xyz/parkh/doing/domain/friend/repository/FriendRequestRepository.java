package xyz.parkh.doing.domain.friend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.parkh.doing.domain.friend.entity.FriendApplication;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.friend.model.FriendshipState;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendApplication, Long> {
    public List<FriendApplication> findAllByTargetAndFriendshipState(User targetUser, FriendshipState friendshipState);

    public List<FriendApplication> findAllByRequester_AuthIdAndFriendshipState(String requesterAuthId, FriendshipState friendshipState);

    public List<FriendApplication> findAllByRequester_AuthIdAndTarget_AuthId(String requesterAuthId, String targetAuthId);
}
