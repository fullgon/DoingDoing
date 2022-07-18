package xyz.parkh.doing.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.model.User;
import xyz.parkh.doing.domain.model.UserInfo;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class UserEntityServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void userSaveAfterFind() {
        User user = User.builder().userId("parkId").name("parkName").build();
        UserInfo saveUser = userService.signUp(user);

        System.out.println("user = " + user);
        System.out.println("saveUser = " + saveUser);

        Assert.assertEquals(user.getUserId(), saveUser.getUserId());
        Assert.assertEquals(user.getName(), saveUser.getName());
    }

}
