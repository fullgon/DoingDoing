package xyz.parkh.doing.service.auth;

import xyz.parkh.doing.domain.AuthVo;
import xyz.parkh.doing.domain.UserVo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface AuthService {
    AuthVo read(String userId);

    Map<String, Object> postSignIn(AuthVo authVo, HttpServletResponse response) throws IOException;

    Map<String, Object> postSignUp(AuthVo authVo, UserVo userVo);

    Map<String, Object> postSendAuthKey(UserVo userVO);
}
