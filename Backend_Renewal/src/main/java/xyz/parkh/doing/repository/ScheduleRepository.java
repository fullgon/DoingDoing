package xyz.parkh.doing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.parkh.doing.domain.entity.schedule.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
