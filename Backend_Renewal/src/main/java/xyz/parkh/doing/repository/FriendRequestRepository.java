package xyz.parkh.doing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.parkh.doing.domain.entity.friend.FriendRequest;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

}
