package xyz.parkh.doing.domain;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.entity.user.IndividualUser;
import xyz.parkh.doing.domain.entity.user.User;

import javax.persistence.EntityManager;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class UserTests {

    @Autowired
    EntityManager em;

    @Test
    public void equalsUser() {
        // TODO 지연 로딩 제외, 추후 올바른 방법으로 수정
        String userId = "userId";
        String password = "password";
        String name = "name";
        String email = "email";
        String company = "company";
        User individualUser = new IndividualUser(userId, password, name, email, company);

        em.persist(individualUser);

        em.flush();
        em.clear();

        User findUser = em.find(User.class, individualUser.getNo());
        Assertions.assertEquals(individualUser, findUser);

    }
}
