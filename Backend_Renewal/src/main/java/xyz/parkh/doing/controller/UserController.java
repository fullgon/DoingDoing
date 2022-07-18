package xyz.parkh.doing.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.parkh.doing.domain.model.User;
import xyz.parkh.doing.domain.model.UserInfo;
import xyz.parkh.doing.service.UserService;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody User user) {
        userService.signUp(user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserInfo> getUserInfo(@PathVariable("userId") String userId) {
        UserInfo userInfo = userService.findUserInfo(userId);
        return ResponseEntity.ok().body(userInfo);
    }

    @PutMapping
    public ResponseEntity modify(@RequestBody UserInfo userInfo) {
        userService.modifyUserInfo(userInfo);
        return ResponseEntity.noContent().build();
    }

    // TODO 현재 로그인 한 사용자 만 삭제할 수 있도록 변경
    @DeleteMapping
    public ResponseEntity remove(@RequestBody UserInfo userInfo) {
        String userId = userInfo.getUserId();
        userService.remove(userId);
        return ResponseEntity.noContent().build();
    }
}
