package xyz.parkh.doing.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.schedule.model.OpenScope;
import xyz.parkh.doing.domain.schedule.model.Period;
import xyz.parkh.doing.domain.schedule.model.ScheduleDTO;
import xyz.parkh.doing.domain.schedule.model.ScheduleType;
import xyz.parkh.doing.domain.schedule.repository.ScheduleRepository;
import xyz.parkh.doing.domain.user.repository.UserRepository;
import xyz.parkh.doing.domain.schedule.service.ScheduleService;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ScheduleServiceTest {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    @Rollback(value = false)
    public void 일정_추가() {
        User user = User.builder().name("name").build();
        userRepository.save(user);
        Period period = Period.createTodayMonthlyPeriod();

//        ToDoSchedule schedule = ToDoSchedule.builder()
//                .user(user).title("title").openScope(OpenScope.PUBIC)
//                .period(period).isCompleted(true).build();
//
//        scheduleRepository.save(schedule);

        ScheduleDTO scheduleDTO = ScheduleDTO.builder()
                .user(user).title("title")
                .openScope(OpenScope.PUBIC).period(period).scheduleType(ScheduleType.TODO)
                .isCompleted(true).build();

        scheduleService.addScheduleForScheduleDTO(scheduleDTO);
    }
}