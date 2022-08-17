package xyz.parkh.doing.domain.friend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.parkh.doing.domain.friend.entity.Friendship;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
}
