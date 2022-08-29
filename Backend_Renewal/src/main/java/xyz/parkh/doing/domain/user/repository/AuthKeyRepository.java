package xyz.parkh.doing.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.parkh.doing.domain.user.entity.AuthKey;

public interface AuthKeyRepository extends JpaRepository<AuthKey, Long> {
}
