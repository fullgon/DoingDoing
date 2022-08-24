package xyz.parkh.doing.domain.friend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.parkh.doing.domain.friend.entity.Friendship;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    Friendship findAllByUser1_AuthIdAndUser2_AuthId(String authId1, String authId2);
}
