package xyz.parkh.doing.domain.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.parkh.doing.domain.schedule.entity.Schedule;
import xyz.parkh.doing.domain.schedule.model.Period;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    public List<Schedule> findAllByPeriod(Period period);

}
