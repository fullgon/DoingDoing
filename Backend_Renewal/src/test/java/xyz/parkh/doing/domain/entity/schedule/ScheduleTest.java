package xyz.parkh.doing.domain.entity.schedule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.schedule.entity.HabitSchedule;
import xyz.parkh.doing.domain.schedule.model.PeriodType;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.schedule.entity.ToDoSchedule;
import xyz.parkh.doing.domain.schedule.model.OpenScope;
import xyz.parkh.doing.domain.schedule.model.Period;
import xyz.parkh.doing.domain.user.repository.UserRepository;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ScheduleTest {

    @Autowired
    EntityManager em;

    @Autowired
    UserRepository userRepository;

    @Test
    public void 월간_할일() {
        User user1 = User.builder().name("USER1NAME").authId("USER1AUTHID").build();
        em.persist(user1);

        ToDoSchedule toDoSchedule = new ToDoSchedule(user1, "ToDoTitle", OpenScope.PUBLIC, Period.createMonthlyPeriod(2022, 8), true);
        em.persist(toDoSchedule);

        em.flush();
        em.clear();

        ToDoSchedule findToDoSchedule = em.find(ToDoSchedule.class, toDoSchedule.getId());

        assertEquals(toDoSchedule, findToDoSchedule);
        assertEquals(toDoSchedule, findToDoSchedule);
        assertEquals(toDoSchedule.getClass(), toDoSchedule.getClass());
    }

    @Test
    public void 월간_습관() {
        User user1 = User.builder().name("USER1NAME").authId("USER1AUTHID").build();
        em.persist(user1);

        HabitSchedule habitSchedule = new HabitSchedule(user1, "HabitTitle", OpenScope.PUBLIC, Period.createMonthlyPeriod(2022, 8));
        em.persist(habitSchedule);

        em.flush();
        em.clear();

        HabitSchedule findHabitSchedule = em.find(HabitSchedule.class, habitSchedule.getId());

        assertEquals(habitSchedule, findHabitSchedule);
        assertEquals(findHabitSchedule.getPeriod().getPeriodType(), PeriodType.MONTH);
        assertEquals(findHabitSchedule.getClass(), HabitSchedule.class);
    }


}
