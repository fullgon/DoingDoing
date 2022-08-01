package xyz.parkh.doing.domain;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.entity.schedule.Schedule;
import xyz.parkh.doing.domain.entity.schedule.ToDoSchedule;
import xyz.parkh.doing.domain.entity.user.Individual;
import xyz.parkh.doing.domain.entity.user.User;
import xyz.parkh.doing.domain.model.schedule.OpenScope;
import xyz.parkh.doing.domain.model.schedule.Period;
import xyz.parkh.doing.repository.ScheduleRepository;
import xyz.parkh.doing.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ScheduleTests {

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
        Individual individual = Individual.builder().userId("parkId").name("parkName").build();
        userRepository.save(individual);

        ToDoSchedule toDoSchedule = new ToDoSchedule();
        toDoSchedule.setTitle("일정 제목");
        toDoSchedule.setOpenScope(OpenScope.PUBIC);
        toDoSchedule.setUser(individual);
        toDoSchedule.setIsCompleted(true);
        scheduleRepository.save(toDoSchedule);

        Schedule findBySchedule = scheduleRepository.findByNo(toDoSchedule.getNo());
        ToDoSchedule convertSchedule = (ToDoSchedule) findBySchedule;
        Boolean isCompleted = convertSchedule.getIsCompleted();
        assertTrue(isCompleted);
    }

    @Test
    public void toDoScheduleBuilderTest() {
        User user = new Individual("userId", "password", "name", "email", "company");
        String title = "title";
        String content = "content";
        OpenScope openScope = OpenScope.PUBIC;
        Period period = new Period();
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

}
