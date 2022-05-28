package xyz.parkh.doing.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xyz.parkh.doing.exception.RequiredValueNullException;
import xyz.parkh.doing.service.email.EmailService;
import xyz.parkh.doing.test.TestService;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class ExceptionTests {

    @Autowired
    TestService testService;

    @Test
    public void requiredValueNullExceptionTest() {
        throw new RequiredValueNullException("Test 에서 준 코드");
    }

}
