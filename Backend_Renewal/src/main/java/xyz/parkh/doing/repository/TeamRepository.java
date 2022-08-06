package xyz.parkh.doing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.parkh.doing.domain.entity.user.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
