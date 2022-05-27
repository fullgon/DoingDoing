package xyz.parkh.doing.etc;

import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@Log4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class PrivateValue {

    @Value("${project.artifactId}")
    private String test;

    @Test
    public void xml_에서_가져오기() {

        System.out.println("test = " + test);


    }

    @Test
    public void 환경변수에서_가져오기() {


    }


    @Test
    public void properties_에서_가져오기() {


    }

}
