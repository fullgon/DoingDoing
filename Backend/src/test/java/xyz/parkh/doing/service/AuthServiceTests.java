package xyz.parkh.doing.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xyz.parkh.doing.domain.UserVo;
import xyz.parkh.doing.exception.RequiredValueNullException;
import xyz.parkh.doing.test.TestService;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class AuthServiceTests {

    @Autowired
    AuthService authService;

    @Test
    public void 이메일_전송_오류() {
        UserVo failUser = new UserVo().builder().userId("park").email("fail").build();

        authService.sendAuthKey(failUser);
    }

}
