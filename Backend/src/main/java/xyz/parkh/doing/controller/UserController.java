package xyz.parkh.doing.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.*;
import xyz.parkh.doing.domain.AuthVo;
import xyz.parkh.doing.domain.UserVo;
import xyz.parkh.doing.service.auth.AuthService;
import xyz.parkh.doing.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Log4j
@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    // 사용자 정보 조회
    @GetMapping("/{userId}")
    public Map<String, Object> getByUserId(@PathVariable("userId") String userId) {
        return userService.getByUserId(userId);
    }

    // 사용자 정보 수정
    @PutMapping
    public Map<String, Object> putByUserIdPut(AuthVo authVo, UserVo userVo, HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        return userService.patchByUserId(userId, userVo);
    }

    // 사용자 정보 삭제
    @DeleteMapping
    public Map<String, Object> deleteByUserId(HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        return userService.deleteByUserId(userId);
    }
}
