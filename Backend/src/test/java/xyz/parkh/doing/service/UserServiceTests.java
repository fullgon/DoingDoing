//package xyz.parkh.doing.service;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.BootstrapWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
//import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import xyz.parkh.doing.domain.UserVo;
//import xyz.parkh.doing.service.user.UserService;
//
//
////@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
////        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
//@RunWith(SpringRunner.class)
////@WebAppConfiguration("file:src/main/webapp")
//@SpringJUnitWebConfig(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
//        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
//public class UserServiceTests {
//
//    @Autowired
//    UserService userService;
//
//    @Test
//    public void test() {
//        UserVo user = userService.getUserByUserId("park");
//        Assert.assertNotNull(user);
//    }
//
//}
