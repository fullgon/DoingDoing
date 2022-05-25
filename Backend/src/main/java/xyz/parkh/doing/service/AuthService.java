package xyz.parkh.doing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.AuthKeyVo;
import xyz.parkh.doing.domain.AuthVo;
import xyz.parkh.doing.domain.UserAuthVo;
import xyz.parkh.doing.domain.UserVo;
import xyz.parkh.doing.interceptor.JwtManager;
import xyz.parkh.doing.mapper.AuthMapper;
import xyz.parkh.doing.mapper.UserMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Log4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserMapper userMapper;
    private final AuthMapper authMapper;

    public AuthVo read(String userId) {
        AuthVo authVo = authMapper.selectByUserId(userId);
        return authVo;
    }

    public Map<String, Object> signIn(AuthVo authVo, HttpServletResponse response) throws IOException {
        HashMap<String, Object> jsonData = new HashMap<>();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if ("".equals(authVo.getUserId()) || "".equals(authVo.getPassword())) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        // 사용자 정보
        AuthVo existAuthVo = new AuthVo();
        existAuthVo.setUserId(authVo.getUserId());
        existAuthVo = read(existAuthVo.getUserId());

        // 사용자 정보 있음
        if (existAuthVo != null) {
            // 비밀번호 틀림
            if (!passwordEncoder.matches(authVo.getPassword(), existAuthVo.getPassword())) {
                jsonData.put("result", "오류");
                jsonData.put("message", "비밀번호가 틀렸습니다.");
            }

            // 로그인 성공
            else {
                // 사용자 JWT
                String jwt = JwtManager.generateToken(existAuthVo.getUserId());
                jsonData.put("jwt", jwt);

                // 사용자 정보
                existAuthVo.setPassword(null);
                jsonData.put("user", existAuthVo);
            }
        } else {
            jsonData.put("result", "오류");
            jsonData.put("message", "사용자가 없습니다.");
        }

        return jsonData;
    }

    public Map<String, Object> signUp(UserAuthVo userAuthVo) {
        Map<String, Object> jsonData = new HashMap<>();
        HashMap<String, Object> result = new HashMap<>();

        String userId = userAuthVo.getUserId();
        String email = userAuthVo.getEmail();

        UserVo existUser = userMapper.selectByUserId(userId);
        if (existUser == null) {
            String hashedPassword = BCrypt.hashpw(userAuthVo.getPassword(), BCrypt.gensalt(12));
            userAuthVo.setPassword(hashedPassword);

//            TODO
//            UserAuthVo -> UserVo
//            UserAuthVo -> AuthVo
//
//            userService.create(userVo);
//            authMapper.insert(authVo);

            result.put("result", "성공");
            result.put("message", "회원 가입 성공");
        } else if (userId.equals(existUser.getUserId())) {
            result.put("result", "오류");
            result.put("message", "아이디가 존재합니다.");
        } else if (email.equals(existUser.getEmail())) {
            result.put("result", "오류");
            result.put("message", "동일한 이메일이 존재합니다.");
        }
        jsonData.put("result", result);

        return jsonData;
    }

    public Map<String, Object> sendAuthKey(UserVo UserVo) {
        HashMap<String, Object> jsonData = new HashMap<>();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", UserVo);
        jsonData.put("result", result);

        return jsonData;
    }

    public Map<String, Object> checkUserId(String userId) {
        HashMap<String, Object> jsonData = new HashMap<>();
        HashMap<String, Object> result = new HashMap<>();

        result.put("result", "result");
        result.put("message", userId);
        jsonData.put("result", result);

        return jsonData;
    }

    public Map<String, Object> checkAuthKey(AuthKeyVo authKeyVo) {
        HashMap<String, Object> jsonData = new HashMap<>();
        jsonData.put("jwt", "jwt");

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", authKeyVo);
        jsonData.put("result", result);

        return jsonData;
    }

    public Map<String, Object> checkPassword(String userIdInJwt, AuthVo authVo) {
        // TODO JWT 체크
        HashMap<String, Object> jsonData = new HashMap<>();
        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", authVo);

        jsonData.put("result", result);

        return jsonData;
    }

    public Map<String, Object> changePassword(String userIdInJwt, AuthVo authVo) {
        // TODO JWT 체크
        HashMap<String, Object> jsonData = new HashMap<>();
        authVo.setPassword(null);
        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", authVo);

        jsonData.put("result", result);

        return jsonData;
    }
}
