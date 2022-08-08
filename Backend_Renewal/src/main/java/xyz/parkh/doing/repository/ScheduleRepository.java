package xyz.parkh.doing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.parkh.doing.domain.entity.schedule.Schedule;
import xyz.parkh.doing.domain.model.schedule.Period;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    public List<Schedule> findAllByPeriod(Period period);

}
