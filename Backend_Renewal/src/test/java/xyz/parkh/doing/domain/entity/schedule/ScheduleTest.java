package xyz.parkh.doing.domain.entity.schedule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.schedule.entity.Schedule;
import xyz.parkh.doing.domain.schedule.entity.ToDoSchedule;
import xyz.parkh.doing.domain.schedule.model.OpenScope;
import xyz.parkh.doing.domain.schedule.model.Period;
import xyz.parkh.doing.domain.schedule.repository.ScheduleRepository;
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
    ScheduleRepository scheduleRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void readExtendsClass() {
        // Schedule 을 상속 받는 클래스 별로 Repository 가 필요한가?를 검증 하기 위한 테스트
        // 이건 Java 지식이 부족 했던거 인듯
        // 조회 할 때 알아서 업 캐스팅 되고,
        // 필요시 다운 캐스팅 해서 사용 하면 되겠다.
        User user = User.builder().authId("parkId").name("parkName").build();
        userRepository.save(user);


        ToDoSchedule toDoSchedule = ToDoSchedule.createMonthlyToDoSchedule(user, "title", OpenScope.PUBIC, true);
        scheduleRepository.save(toDoSchedule);

        Schedule findBySchedule = scheduleRepository.findById(toDoSchedule.getId()).get();
        ToDoSchedule convertSchedule = (ToDoSchedule) findBySchedule;
        Boolean isCompleted = convertSchedule.getIsCompleted();
        assertTrue(isCompleted);
    }

    @Test
    public void toDoScheduleTest() {
        User user = new User("userId", "password", "name", "email", "company");
        String title = "title";
        String content = "content";
        OpenScope openScope = OpenScope.PUBIC;
        Period period = Period.createMonthlyPeriod();
        Boolean isCompleted = true;

        ToDoSchedule toDoSchedule = ToDoSchedule.createMonthlyToDoSchedule(user, title, OpenScope.PUBIC, isCompleted);

        assertEquals(toDoSchedule.getTitle(), title);
        assertEquals(toDoSchedule.getUser(), user);
        assertEquals(toDoSchedule.getOpenScope(), openScope);
        assertEquals(toDoSchedule.getPeriod(), period);
        assertEquals(toDoSchedule.getIsCompleted(), isCompleted);
    }


    @Test
    public void equalsSchedule() {
        User user1 = new User("userId", "password", "name", "email", "company");
        User user2 = new User("userId", "password", "name", "email", "company");
        String title = "title";
        String content = "content";
        OpenScope openScope = OpenScope.PUBIC;
        Boolean isCompleted = true;
        Schedule schedule1 = ToDoSchedule.createDailyToDoSchedule(user1, title, openScope, isCompleted);
        Schedule schedule2 = ToDoSchedule.createDailyToDoSchedule(user2, title, openScope, isCompleted);
        assertEquals(schedule1, schedule2);
    }

    @Test
    public void saveSchedule() {
        // TODO 지연 로딩 제외, 추후 올바른 방법으로 수정
        User user = new User("userId", "password", "name", "email", "company");
        em.persist(user);

        String title = "title";
        String content = "content";
        OpenScope openScope = OpenScope.PUBIC;
        Boolean isCompleted = true;

        Schedule schedule = ToDoSchedule.createMonthlyToDoSchedule(user, title, openScope, isCompleted);
        em.persist(schedule);

        em.flush();
        em.clear();

        ToDoSchedule findSchedule = (ToDoSchedule) em.find(Schedule.class, schedule.getId());

        assertEquals(schedule.getId(), findSchedule.getId());
        assertEquals(schedule.getTitle(), findSchedule.getTitle());
        assertEquals(schedule.getOpenScope(), findSchedule.getOpenScope());
        assertEquals(((ToDoSchedule) schedule).getIsCompleted(), findSchedule.getIsCompleted());
        assertEquals(schedule.getPeriod().getPeriodType(), findSchedule.getPeriod().getPeriodType());
    }

}
