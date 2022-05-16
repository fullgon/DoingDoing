package xyz.parkh.campus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.parkh.campus.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUserId(String userId);
    Boolean existsByUserId(String UserId);
}
