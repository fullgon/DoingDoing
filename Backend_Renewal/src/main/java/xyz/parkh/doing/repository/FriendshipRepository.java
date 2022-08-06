package xyz.parkh.doing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.parkh.doing.domain.entity.friend.Friendship;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
}
