package xyz.parkh.doing.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.model.IndividualDetailInfo;
import xyz.parkh.doing.domain.model.UserInfo;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class IndividualUserDetailInfoEntityServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void userSaveAfterFind() {
        IndividualDetailInfo individualDetailInfo = IndividualDetailInfo.builder().userId("parkId").name("parkName").build();
        UserInfo saveUser = userService.signUp(individualDetailInfo);

        System.out.println("user = " + individualDetailInfo);
        System.out.println("saveUser = " + saveUser);

        Assert.assertEquals(individualDetailInfo.getUserId(), saveUser.getUserId());
        Assert.assertEquals(individualDetailInfo.getName(), saveUser.getName());
    }

}
