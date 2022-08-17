package xyz.parkh.doing.domain.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.parkh.doing.domain.team.entity.TeamUser;

public interface TeamUserRepository extends JpaRepository<TeamUser, Long> {
}
