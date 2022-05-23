package xyz.parkh.doing.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.parkh.doing.domain.AuthKeyVo;
import xyz.parkh.doing.domain.AuthVo;
import xyz.parkh.doing.domain.UserVo;
import xyz.parkh.doing.interceptor.JwtManager;
import xyz.parkh.doing.service.auth.AuthService;
import xyz.parkh.doing.service.user.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Log4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @PostMapping("/signIn")
    public Map<String, Object> postSignIn(AuthVo authVo, UserVo userVo, HttpServletResponse response) throws IOException {
        HashMap<String, Object> jsonData = new HashMap<>();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if ("".equals(authVo.getUserId()) || "".equals(authVo.getPassword())) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        // 사용자 정보
        AuthVo existAuthVo = new AuthVo();
        existAuthVo.setUserId(authVo.getUserId());
        existAuthVo = authService.read(existAuthVo.getUserId());

        String hashedPassword = BCrypt.hashpw(authVo.getPassword(), BCrypt.gensalt(12));
        System.out.println(hashedPassword);
        // 사용자 정보 있음
        if (existAuthVo != null) {
            // 비밀번호 틀림
            System.out.println("existAuthVo = " + existAuthVo);
            System.out.println("authVo = " + authVo);
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

    // 회원 가입
    @PostMapping("/signUp")
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
            authService.create(authVo);

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

    // 인증 번호 전송
    @PostMapping("/sendAuthkey")
    public Map<String, Object> postSendAuthkey(UserVo userVO) {
        HashMap<String, Object> jsonData = new HashMap<>();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", "message");
        jsonData.put("result", result);

        return jsonData;
    }

    // 인증 번호 확인
    @PostMapping("/checkAuthKey")
    public Map<String, Object> postCheckAuthKey(UserVo userVO, AuthKeyVo authKeyVo) {
        // TODO JWT 체크
        HashMap<String, Object> jsonData = new HashMap<>();
        jsonData.put("jwt", "jwt");

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", "message");
        jsonData.put("result", result);

        return jsonData;
    }

    // 비밀번호 변경
    @PatchMapping("/resetPassword")
    public Map<String, Object> patchResetPassword(UserVo userVO, AuthVo authVo) {
        // TODO JWT 체크
        HashMap<String, Object> jsonData = new HashMap<>();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", "message");

        jsonData.put("result", result);

        return jsonData;
    }

    // 사용자 확인을 위한 비밀번호 재확인
    @PostMapping("/checkPassword")
    public Map<String, Object> postCheckPassword(AuthVo authVo) {
        // TODO JWT 체크
        HashMap<String, Object> jsonData = new HashMap<>();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", "message");

        jsonData.put("result", result);

        return jsonData;
    }
}
