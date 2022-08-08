package xyz.parkh.doing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.parkh.doing.domain.entity.friend.FriendRequest;
import xyz.parkh.doing.domain.entity.user.User;
import xyz.parkh.doing.domain.model.friend.FriendshipState;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    public List<FriendRequest> findAllByTargetAndFriendshipState(User targetUser, FriendshipState friendshipState);

    public List<FriendRequest> findAllByRequesterAndTarget(User target, User requester);
}
