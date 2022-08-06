package xyz.parkh.doing.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.entity.schedule.Schedule;
import xyz.parkh.doing.domain.entity.schedule.ToDoSchedule;
import xyz.parkh.doing.domain.entity.user.User;
import xyz.parkh.doing.domain.model.schedule.OpenScope;
import xyz.parkh.doing.domain.model.schedule.Period;
import xyz.parkh.doing.domain.model.schedule.PeriodType;
import xyz.parkh.doing.repository.ScheduleRepository;
import xyz.parkh.doing.repository.UserRepository;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ScheduleTests {

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

        ToDoSchedule toDoSchedule = ToDoSchedule.builder().title("일정 제목")
                .openScope(OpenScope.PUBIC).user(user)
                .isCompleted(true).build();
        scheduleRepository.save(toDoSchedule);

        Schedule findBySchedule = scheduleRepository.findById(toDoSchedule.getId()).get();
        ToDoSchedule convertSchedule = (ToDoSchedule) findBySchedule;
        Boolean isCompleted = convertSchedule.getIsCompleted();
        assertTrue(isCompleted);
    }

    @Test
    public void toDoScheduleBuilderTest() {
        User user = new User("userId", "password", "name", "email", "company");
        String title = "title";
        String content = "content";
        OpenScope openScope = OpenScope.PUBIC;
        Period period = Period.builder().build();
        Boolean isCompleted = true;

        ToDoSchedule toDoScheduleByBuilder = ToDoSchedule.builder().title(title).content(content)
                .user(user).openScope(openScope).period(period).isCompleted(isCompleted).build();

        assertEquals(toDoScheduleByBuilder.getTitle(), title);
        assertEquals(toDoScheduleByBuilder.getContent(), content);
        assertEquals(toDoScheduleByBuilder.getUser(), user);
        assertEquals(toDoScheduleByBuilder.getOpenScope(), openScope);
        assertEquals(toDoScheduleByBuilder.getPeriod(), period);
        assertEquals(toDoScheduleByBuilder.getIsCompleted(), isCompleted);
    }


    @Test
    public void equalsSchedule() {
        User user1 = new User("userId", "password", "name", "email", "company");
        User user2 = new User("userId", "password", "name", "email", "company");
        String title = "title";
        String content = "content";
        OpenScope openScope = OpenScope.PUBIC;
        Period period1 = Period.builder().periodType(PeriodType.MONTH)
                .year(2022L).month(10L).build();
        Period period2 = Period.builder().periodType(PeriodType.MONTH)
                .year(2022L).month(10L).build();
        Boolean isCompleted = true;

        ToDoSchedule schedule1 = ToDoSchedule.builder().title(title).content(content)
                .user(user1).openScope(openScope).period(period1).isCompleted(isCompleted).build();
        ToDoSchedule schedule2 = ToDoSchedule.builder().title(title).content(content)
                .user(user2).openScope(openScope).period(period2).isCompleted(isCompleted).build();

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
        Period period = Period.builder().periodType(PeriodType.MONTH)
                .year(2022L).month(10L).build();
        Boolean isCompleted = true;

        ToDoSchedule toDoScheduleByBuilder = ToDoSchedule.builder().title(title).content(content)
                .user(user).openScope(openScope).period(period).isCompleted(isCompleted).build();
        em.persist(toDoScheduleByBuilder);

        em.flush();
        em.clear();

        ToDoSchedule findSchedule = (ToDoSchedule) em.find(Schedule.class, toDoScheduleByBuilder.getId());

        assertEquals(toDoScheduleByBuilder.getId(), findSchedule.getId());
        assertEquals(toDoScheduleByBuilder.getTitle(), findSchedule.getTitle());
        assertEquals(toDoScheduleByBuilder.getContent(), findSchedule.getContent());
        assertEquals(toDoScheduleByBuilder.getOpenScope(), findSchedule.getOpenScope());
        assertEquals(toDoScheduleByBuilder.getIsCompleted(), findSchedule.getIsCompleted());
        assertEquals(toDoScheduleByBuilder.getPeriod().getPeriodType(), findSchedule.getPeriod().getPeriodType());
    }

}
