package xyz.parkh.doing.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.model.UserDetailInfo;
import xyz.parkh.doing.domain.model.UserInfo;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    public void userSaveAfterFind() {
        UserDetailInfo userDetailInfo = UserDetailInfo.builder().userId("parkId").name("parkName").build();
        UserInfo saveUser = userService.signUp(userDetailInfo);

        System.out.println("user = " + userDetailInfo);
        System.out.println("saveUser = " + saveUser);

        Assert.assertEquals(userDetailInfo.getUserId(), saveUser.getUserId());
        Assert.assertEquals(userDetailInfo.getName(), saveUser.getName());
    }

}
