package xyz.parkh.doing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.parkh.doing.domain.entity.AuthKey;

public interface AuthKeyRepository extends JpaRepository<AuthKey, Long> {
}
