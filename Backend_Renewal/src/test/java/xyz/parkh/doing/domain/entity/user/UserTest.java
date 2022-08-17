package xyz.parkh.doing.domain.entity.user;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.user.entity.User;

import javax.persistence.EntityManager;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class UserTest {

    @Autowired
    EntityManager em;

    @Test
    public void builderUser() {
        // User <- User
        // User 의 객체 변수의 접근 제어자를 private 에서 protected 로 변경
        // User 에서 toString override 해서 사용 가능
        // 하지만 password 같은 정보를 다른 클래스에서
        // 바로 접근, 변경 가능하다는게 걸려서 private 와 super()

        String authId = "userId";
        String password = "password";
        String name = "name";
        String email = "email";
        String company = "company";

        User userByBuilder = User.builder()
                .authId(authId).password(password).name(name)
                .email(email).company(company)
                .build();

        User UserByConstructor = new User(authId, password, name, email, company);

        System.out.println("UserByConstructor = " + UserByConstructor);
        System.out.println("UserByBuilder = " + userByBuilder);

        Assertions.assertEquals(userByBuilder, UserByConstructor);
    }

    @Test
    public void equalsUser() {
        // TODO 지연 로딩 제외, 추후 올바른 방법으로 수정
        String authId = "userId";
        String password = "password";
        String name = "name";
        String email = "email";
        String company = "company";
        User User = new User(authId, password, name, email, company);

        em.persist(User);

        em.flush();
        em.clear();

        User findUser = em.find(User.class, User.getId());
        Assertions.assertEquals(User, findUser);

    }
}
