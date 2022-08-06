package xyz.parkh.doing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.parkh.doing.domain.entity.user.TeamUser;

public interface TeamUserRepository extends JpaRepository<TeamUser, Long> {
}
