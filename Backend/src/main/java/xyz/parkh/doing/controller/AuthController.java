/**
 * 로그인, 회원가입, 사용자 확인 등
 * 사용자 인증 관련된 요청을 받는 Controller
 */

package xyz.parkh.doing.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.*;
import xyz.parkh.doing.domain.AuthKeyVo;
import xyz.parkh.doing.domain.AuthVo;
import xyz.parkh.doing.domain.UserAuthVo;
import xyz.parkh.doing.domain.UserVo;
import xyz.parkh.doing.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Log4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // UserId, Password
    // 로그인
    @PostMapping("/sign-in")
    public Map<String, Object> postSignIn(@RequestBody AuthVo authVo, HttpServletResponse response) {
        return authService.signIn(authVo, response);
    }

    // userId, password, email, name, company
    // 회원 가입
    @PostMapping("/sign-up")
    public Map<String, Object> postSignUp(@RequestBody UserAuthVo userAuthVo) {
        return authService.signUp(userAuthVo);
    }

    // userId, email
    // 인증 번호 전송
    @PostMapping("/send/auth-key")
    public Map<String, Object> postSendAuthKey(@RequestBody UserVo userVo) {
        return authService.sendAuthKey(userVo);
    }

    // userId, authKey
    // 인증 번호 확인
    @PostMapping("/check/auth-key")
    public Map<String, Object> postCheckAuthKey(@RequestBody AuthKeyVo authKeyVo) {
        return authService.checkAuthKey(authKeyVo);
    }

    // userId
    // id 중복 확인
    @PostMapping("/check/user-id")
    public Map<String, Object> postCheckUserId(@RequestBody AuthVo authVo) {
        System.out.println("authVo = " + authVo);
        return authService.checkUserId(authVo.getUserId());
    }

    // jwt, password
    // 사용자 확인을 위한 비밀번호 재확인
    @PostMapping("/check/password")
    public Map<String, Object> postCheckPassword(@RequestBody AuthVo authVo, HttpServletRequest request) {
        String userIdInJwt = (String) request.getAttribute("userId");

        return authService.checkPassword(userIdInJwt, authVo);
    }

    // jwt, userId, password
    // 비밀번호 변경
    @PatchMapping("/reset/password")
    public Map<String, Object> patchResetPassword(@RequestBody AuthVo authVo, HttpServletRequest request) {
        String userIdInJwt = (String) request.getAttribute("userId");

        return authService.changePassword(userIdInJwt, authVo);
    }
}
