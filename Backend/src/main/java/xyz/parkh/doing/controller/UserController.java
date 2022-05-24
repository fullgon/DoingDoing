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
//    @GetMapping("/{userId}")
//    public Map<String, Object> getByUserId(@PathVariable("userId") String userId) {
//        return userService.getByUserId(userId);
//    }

    // 사용자 정보 수정
    @PutMapping
//    public Map<String, Object> patchByUserId(String name, HttpServletRequest request) {
//    public Map<String, Object> patchByUserId(@RequestBody UserVo userVo, HttpServletRequest request) {
    public Map<String, Object> patchByUserIdPut(AuthVo authVo, UserVo userVo, HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        return userService.patchByUserId(userId, userVo);
    }

    @PostMapping
    public Map<String, Object> patchByUserIdPost(AuthVo authVo, UserVo userVo, HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        return userService.patchByUserId(userId, userVo);
    }

    @PatchMapping("/name")
//    @RequestMapping(value = "", method = RequestMethod.PATCH)
    public Map<String, Object> patchByUserIdPatch(String name) {
//        String userId = (String) request.getAttribute("userId");
        System.out.println("name = " + name);
//        return userService.patchByUserId(userId, userVo);
        return null;
    }

    @RequestMapping(value = "patch", method = RequestMethod.PATCH)
    public Map<String, Object> patchByUserIdRequestPatch(AuthVo authVo, UserVo userVo, HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        return userService.patchByUserId(userId, userVo);
    }

    @RequestMapping(value = "/patch2", method = RequestMethod.PATCH)
    public Map<String, Object> patchByUserIdRequestPatch2(AuthVo authVo, UserVo userVo, HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        return userService.patchByUserId(userId, userVo);
    }

    @RequestMapping(value = "/patch3", method = RequestMethod.PATCH)
    public Map<String, Object> patchByUserIdRequestPatch32(AuthVo authVo, UserVo userVo, HttpServletRequest request) {
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
