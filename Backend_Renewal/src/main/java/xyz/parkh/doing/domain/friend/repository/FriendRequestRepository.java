package xyz.parkh.doing.domain.friend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.parkh.doing.domain.friend.entity.FriendApplication;
import xyz.parkh.doing.domain.friend.model.FriendshipState;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendApplication, Long> {
    List<FriendApplication> findAllByTarget_AuthIdAndFriendshipState(String authId, FriendshipState friendshipState);

    List<FriendApplication> findAllByRequester_AuthIdAndFriendshipState(String authId, FriendshipState friendshipState);

    FriendApplication findByRequester_AuthIdAndTarget_AuthIdAndFriendshipState(String requesterAuthId, String targetAuthId, FriendshipState friendshipState);
}
