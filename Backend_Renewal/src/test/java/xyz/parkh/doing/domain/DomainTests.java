package xyz.parkh.doing.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.schedule.entity.HabitSchedule;
import xyz.parkh.doing.domain.schedule.entity.Schedule;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.schedule.model.OpenScope;

import javax.persistence.EntityManager;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class DomainTests {

    @Autowired
    EntityManager em;

    @Test
    public void equalsToInsertAndFindSchedule() {
        User user = User.builder().authId("PHJ").build();
        em.persist(user);

        Schedule schedule = HabitSchedule.createTodayDaily(user, "title", OpenScope.PUBIC);
        em.persist(schedule);

        Schedule findSchedule = em.find(Schedule.class, schedule.getId());
        Assert.assertTrue(schedule == findSchedule); // 1차 캐시 때문에 == 가능
    }

    @Test
    public void setUserNameForScheduleInUser() {
        User user = User.builder().authId("PHJ").build();
        em.persist(user);

        Schedule schedule = HabitSchedule.createTodayDaily(user, "title", OpenScope.PUBIC);
        em.persist(schedule);

        // DB 에 반영 되었는지 확인
        Schedule findSchedule = em.find(Schedule.class, schedule.getId());
        Assert.assertEquals(findSchedule.getUser(), user);

        // 수정 된 값이 commit 되지 않아도 반영 되는지 확인
        // JPA 는 영속성 컨텍스트가 관리하는 1차 캐시가 존재해,
        // 같은 Tx 내에서 find 해 올 경우 아이디를 비교해
        // 1차 캐시에 있는 객체를 반환한다.
        User scheduleInUser = findSchedule.getUser();
        scheduleInUser.setName("parkh");
        User findUser = em.find(User.class, scheduleInUser.getId());
        Assert.assertEquals(scheduleInUser, findUser);

        // 자바의 객체는 값을 그대로 복사하는 것이 아니라
        // 객체의 주소값을 넘겨주는 것이라 == 비교 가능
        System.out.println("findSchedule.getUser() = " + findSchedule.getUser());
        System.out.println("scheduleInUser = " + scheduleInUser);
        Assert.assertTrue(findSchedule.getUser() == scheduleInUser);
    }

}
