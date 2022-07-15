package xyz.parkh.doing.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.entity.Schedule;
import xyz.parkh.doing.entity.User;

import javax.persistence.EntityManager;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class DomainTests {

    @Autowired
    EntityManager em;

    @Test
    @Transactional(readOnly = false)
    public void 테스트() {

        User user = new User();
        user.setId("PHJ");
        user.setName("parkh");

        System.out.println("user = " + user);
        em.persist(user);
        System.out.println("user = " + user);

        Schedule schedule = new Schedule();
        schedule.setUser(user);

        em.persist(schedule);

        System.out.println("schedule.getUser() = " + schedule.getUser());

        Assert.assertTrue(true);
    }
}
