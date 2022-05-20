package xyz.parkh.doing.mapper;

import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xyz.parkh.doing.domain.UserVo;
import xyz.parkh.doing.utils.Utils;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class UserMapperTests {

    @Autowired
    UserMapper userMapper;

    @Test
    @DisplayName("사용자 등록, 조회, 수정, 삭제")
    public void userCrud() {
        // 테스트 사용자 생성
        UserVo testUser = generateNewUser();
        String testUserId = testUser.getUserId();

        // 사용자 삽입
        int insertStatus = userMapper.insert(testUser);
        Assert.isTrue(insertStatus == 1);

        // 사용자 조회
        UserVo selectUser = userMapper.selectByUserId(testUserId);
        Assert.isTrue(testUserId.equals(selectUser.getUserId()));

        // 사용자 수정
        UserVo newUser = generateNewUser();
        newUser.setUserId(testUserId);

        int updateStatus = userMapper.update(newUser);
        Assert.isTrue(updateStatus == 1);

        // 사용자 삭제
        int deleteStatus = userMapper.delete(testUserId);
        Assert.isTrue(deleteStatus == 1);
    }

    @Test
    @DisplayName("모든 사용자 조회")
    public void allUserSelect() {
        // Given
        List<UserVo> userList;

        // When
        userList = userMapper.selectAll();

        // Then
        Assertions.assertNotNull(userList);
    }


    // 새 사용자 생성
    private UserVo generateNewUser() {

        Utils utils = null;

        UserVo user;
        while (true) {
            user = utils.generateUser();
            if (userMapper.selectByUserId(user.getUserId()) == null) {
                break;
            }
        }
        return user;
    }
}
