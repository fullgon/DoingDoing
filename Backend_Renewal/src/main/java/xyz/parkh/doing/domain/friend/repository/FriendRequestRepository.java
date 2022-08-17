package xyz.parkh.doing.domain.friend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.parkh.doing.domain.friend.entity.FriendRequest;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.friend.model.FriendshipState;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    public List<FriendRequest> findAllByTargetAndFriendshipState(User targetUser, FriendshipState friendshipState);

    public List<FriendRequest> findAllByRequesterAndTarget(User target, User requester);
}
