package xyz.parkh.doing.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.parkh.doing.domain.UserVo;
import xyz.parkh.doing.service.user.UserService;

import java.util.HashMap;
import java.util.Map;


@Log4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    // 사용자 정보 조회
    @GetMapping("/{userId}")
    public Map<String, Object> getByUserId(@PathVariable("userId") String userId) {
        HashMap<String, Object> jsonData = new HashMap<>();
        UserVo user = userService.getUserByUserId(userId);
        jsonData.put("user", user);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", "message");
        jsonData.put("result", result);
        return jsonData;
    }

    // 사용자 정보 수정
    @PatchMapping("/{userId}")
    public Map<String, Object> patchByUserId(@PathVariable("userId") String userId, UserVo userVo) {
        HashMap<String, Object> jsonData = new HashMap<>();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", "message");
        jsonData.put("result", result);
        return jsonData;
    }

    // 사용자 정보 삭제
    @DeleteMapping("/{userId}")
    public Map<String, Object> deleteByUserId(@PathVariable("userId") String userId) {
        HashMap<String, Object> jsonData = new HashMap<>();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", "message");
        jsonData.put("result", result);
        return jsonData;
    }
}
