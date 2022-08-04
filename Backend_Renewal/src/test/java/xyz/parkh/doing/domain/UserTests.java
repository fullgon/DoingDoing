package xyz.parkh.doing.domain;

import org.junit.Assert;
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
    public void builderUser() {
        // User <- IndividualUser
        // User 의 객체 변수의 접근 제어자를 private 에서 protected 로 변경
        // IndividualUser 에서 toString override 해서 사용 가능
        // 하지만 password 같은 정보를 다른 클래스에서
        // 바로 접근, 변경 가능하다는게 걸려서 private 와 super()

        String userId = "userId";
        String password = "password";
        String name = "name";
        String email = "email";
        String company = "company";

        IndividualUser individualUserByBuilder = IndividualUser.builder()
                .userId(userId).password(password).name(name)
                .email(email).company(company)
                .build();

        User individualUserByConstructor = new IndividualUser(userId, password, name, email, company);

        System.out.println("individualUserByConstructor = " + individualUserByConstructor);
        System.out.println("individualUserByBuilder = " + individualUserByBuilder);

        Assertions.assertEquals(individualUserByBuilder, individualUserByConstructor);
    }

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
