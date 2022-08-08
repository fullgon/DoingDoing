package xyz.parkh.doing.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.entity.schedule.ToDoSchedule;
import xyz.parkh.doing.domain.entity.user.User;
import xyz.parkh.doing.domain.model.schedule.Period;
import xyz.parkh.doing.domain.model.schedule.PeriodType;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ScheduleRepositoryTest {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Rollback(value = false)
    public void 일정_조회() {
        User user = User.builder().name("user1Name").authId("user1ID").build();
        userRepository.save(user);

//        Period period = new Period(2022L, 8L, 8L, PeriodType.MONTH);


//        ToDoSchedule.builder().user();


//        scheduleRepository.findAllByPeriod();
    }
}