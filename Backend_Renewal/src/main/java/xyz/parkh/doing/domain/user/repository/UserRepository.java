package xyz.parkh.doing.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.parkh.doing.domain.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByAuthId(String authId);
    User findByEmail(String email);
}
