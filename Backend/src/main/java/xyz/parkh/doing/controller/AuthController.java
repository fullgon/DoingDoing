package xyz.parkh.doing.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.parkh.doing.domain.AuthKeyVo;
import xyz.parkh.doing.domain.AuthVo;
import xyz.parkh.doing.domain.UserVo;
import xyz.parkh.doing.service.auth.AuthService;
import xyz.parkh.doing.service.user.UserService;

import java.util.HashMap;
import java.util.Map;

@Log4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

//    @PostMapping("/signIn")
//    public Map<String, Object> postSignIn(AuthVo authVo, UserVo userVo, HttpServletResponse response) throws IOException {
//        HashMap<String, Object> jsonData = new HashMap<>();
//
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//        if ("".equals(authVo.getUserId()) || "".equals(authVo.getPassword())) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//        }
//
//        // 사용자 정보
//        UserVo existUserVo = new UserVo();
//        existUserVo.setUserId(userVo.getUserId());
//        existUserVo = userService.read(existUserVo);
//
//        // 사용자 정보 있음
//        if (existUserVo != null) {
//            // 비밀번호 틀림
//            if (!passwordEncoder.matches(authVo.getPassword(), existUserVo.getPassword())) {
//                jsonData.put("result", "오류");
//                jsonData.put("message", "비밀번호가 틀렸습니다.");
//            }
//
//            // 로그인 성공
//            else {
//                String userName = existUserVo.getUserName();
//                String userType = existUserVo.getUserType();
//                String fcmToken = existUserVo.getFcmToken();
//
//                // 사용자 JWT
//                String jwt = JwtManager.generateToken(existUserVo.getUserId(), userName, userType);
//                jsonData.put("jwt", jwt);
//
//                // 사용자 정보
//                existUserVo.setUserPassword(null);
//                jsonData.put("user", existUserVo);
//            }
//        } else {
//            jsonData.put("result", "오류");
//            jsonData.put("message", "사용자가 없습니다.");
//        }
//
//        return jsonData;
//    }

    // 회원 가입
    @PostMapping("/signUp")
    public Map<String, Object> postSignUp(AuthVo authVO, UserVo userVO) {
        HashMap<String, Object> jsonData = new HashMap<>();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", "message");
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
