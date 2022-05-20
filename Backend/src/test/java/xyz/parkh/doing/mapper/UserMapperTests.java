package xyz.parkh.doing.mapper;

import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import xyz.parkh.doing.domain.UserVo;

//@WebAppConfiguration("file:src/main/webapp")
//@ContextHierarchy({
//        @ContextConfiguration("file:/WEB-INF/spring/root-context.xml"),
//        @ContextConfiguration("file:/WEB-INF/spring/appServlet/servlet-context.xml")
//})
//@RunWith(SpringRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
//        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class UserMapperTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void getUser() {
        UserVo user = userMapper.selectUserByUserId("park");
        System.out.println("user = " + user);
        Assert.notNull(user);
    }
}
