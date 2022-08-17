package xyz.parkh.doing.domain.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.parkh.doing.domain.team.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
