package xyz.parkh.doing.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.entity.schedule.ToDoSchedule;
import xyz.parkh.doing.domain.entity.user.User;
import xyz.parkh.doing.domain.model.schedule.OpenScope;
import xyz.parkh.doing.domain.model.schedule.Period;
import xyz.parkh.doing.domain.model.schedule.ScheduleDTO;
import xyz.parkh.doing.domain.model.schedule.ScheduleType;
import xyz.parkh.doing.repository.ScheduleRepository;
import xyz.parkh.doing.repository.UserRepository;

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
        Period period = new Period();

//        ToDoSchedule schedule = ToDoSchedule.builder()
//                .user(user).title("title").content("content").openScope(OpenScope.PUBIC)
//                .period(period).isCompleted(true).build();
//
//        scheduleRepository.save(schedule);

        ScheduleDTO scheduleDTO = ScheduleDTO.builder()
                .user(user).title("title").content("content")
                .openScope(OpenScope.PUBIC).period(period).scheduleType(ScheduleType.TODO)
                .isCompleted(true).build();

        scheduleService.addScheduleForScheduleDTO(scheduleDTO);
    }
}