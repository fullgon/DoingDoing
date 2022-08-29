package xyz.parkh.doing.domain.friend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.parkh.doing.domain.friend.entity.Friendship;
import xyz.parkh.doing.domain.user.entity.User;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    Friendship findByUser1_AuthIdAndUser2_AuthId(String authId1, String authId2);

    List<Friendship> findAllByUser1(User user1);


    @Query("select f.user2 from Friendship f where f.user1 = :user")
    List<User> getFriendList(@Param("user") User user);
}
