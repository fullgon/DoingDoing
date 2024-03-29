package xyz.parkh.doing.domain.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.parkh.doing.domain.schedule.entity.Schedule;
import xyz.parkh.doing.domain.schedule.model.OpenScope;
import xyz.parkh.doing.domain.schedule.model.Period;
import xyz.parkh.doing.domain.schedule.model.ScheduleType;
import xyz.parkh.doing.domain.user.entity.User;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByUserAndPeriod(User user, Period period);
}
