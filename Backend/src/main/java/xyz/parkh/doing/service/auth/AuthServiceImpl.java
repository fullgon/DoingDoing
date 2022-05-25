package xyz.parkh.doing.service.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.AuthVo;
import xyz.parkh.doing.domain.UserVo;
import xyz.parkh.doing.interceptor.JwtManager;
import xyz.parkh.doing.mapper.AuthMapper;
import xyz.parkh.doing.service.user.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Log4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthMapper authMapper;

    private final UserService userService;

    public AuthVo read(String userId) {
        AuthVo authVo = authMapper.selectByUserId(userId);
        return authVo;
    }

    @Override
    public Map<String, Object> postSignIn(AuthVo authVo, HttpServletResponse response) throws IOException {
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
                String userName = userService.read(existAuthVo.getUserId()).getName();

                // 사용자 JWT
                String jwt = JwtManager.generateToken(existAuthVo.getUserId(), userName);
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

    @Override
    public Map<String, Object> postSignUp(AuthVo authVo, UserVo userVo) {
        Map<String, Object> jsonData = new HashMap<>();
        HashMap<String, Object> result = new HashMap<>();

        String userId = authVo.getUserId();
        String email = userVo.getEmail();

        UserVo existUser = userService.read(userId);

        if (existUser == null) {
            String hashedPassword = BCrypt.hashpw(authVo.getPassword(), BCrypt.gensalt(12));
            authVo.setPassword(hashedPassword);

            userService.create(userVo);
            authMapper.insert(authVo);

            result.put("result", "성공");
            result.put("message", "회원 가입 성공");
        } else if (userId.equals(existUser.getUserId())) {
            result.put("result", "오류");
            result.put("message", "아이디가 존재합니다.");
        } else if (email.equals(existUser.getEmail())) {
            result.put("result", "오류");
            result.put("message", "동일한 이메일이 존재합니다.");
        }

        System.out.println("email = " + email);
        System.out.println("existUser.getEmail() = " + existUser.getEmail());
        jsonData.put("result", result);

        return jsonData;
    }

    @Override
    public Map<String, Object> postSendAuthKey(UserVo userVO) {
        HashMap<String, Object> jsonData = new HashMap<>();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", "message");
        jsonData.put("result", userVO);

        return jsonData;
    }

}
