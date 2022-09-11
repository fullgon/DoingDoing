package xyz.parkh.doing.domain.friend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.parkh.doing.domain.friend.entity.FriendApplication;
import xyz.parkh.doing.domain.friend.model.FriendshipState;
import xyz.parkh.doing.domain.user.entity.User;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendApplication, Long> {

    List<FriendApplication> findAllByTargetAndFriendshipState(User target, FriendshipState friendshipState);

    List<FriendApplication> findAllByRequesterAndFriendshipState(User requester, FriendshipState friendshipState);

    FriendApplication findByRequesterAndTargetAndFriendshipState(User requester, User target, FriendshipState friendshipState);

    FriendApplication findTopByRequesterAndTargetOrderByIdDesc(User requester, User target);
}
