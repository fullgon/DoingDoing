package xyz.parkh.doing.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.parkh.doing.domain.AuthKeyVo;
import xyz.parkh.doing.domain.AuthVo;
import xyz.parkh.doing.domain.UserVo;
import xyz.parkh.doing.service.auth.AuthService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Log4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signIn")
    public Map<String, Object> postSignIn(AuthVo authVo, HttpServletResponse response) throws IOException {
        return authService.postSignIn(authVo, response);
    }

    // 회원 가입
    @PostMapping("/signUp")
    public Map<String, Object> postSignUp(AuthVo authVo, UserVo userVo) {
        return authService.postSignUp(authVo, userVo);
    }

    // 인증 번호 전송
    @PostMapping("/sendAuthKey")
    public Map<String, Object> postSendAuthKey(UserVo userVO) {
        return authService.postSendAuthKey(userVO);
    }

    // 인증 번호 확인
    @PostMapping("/checkAuthKey")
    public Map<String, Object> postCheckAuthKey(UserVo userVO, AuthKeyVo authKeyVo) {
        HashMap<String, Object> jsonData = new HashMap<>();
        jsonData.put("jwt", "jwt");

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", userVO);
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
        result.put("message", userVO);

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
        result.put("message", authVo.getUserId());

        jsonData.put("result", result);

        return jsonData;
    }
}
