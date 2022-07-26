package xyz.parkh.doing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.parkh.doing.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByNo(Long no);
    User findByUserId(String userId);

    User findByEmail(String email);
}
