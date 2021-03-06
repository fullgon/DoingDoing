package xyz.parkh.doing.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.parkh.doing.domain.model.Auth;
import xyz.parkh.doing.domain.model.Check;
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

    @PostMapping("/sign-in")
    public ResponseEntity<Check> signIn(@RequestBody Auth auth) {
        Boolean isSuccess = userService.signIn(auth);
        return ResponseEntity.ok().body(new Check(isSuccess));
    }

    @PostMapping("/check/user-id")
    public ResponseEntity<Check> checkUserId(@RequestBody Auth auth) {
        Boolean isAbleSignUpUserId = userService.isAbleSignUpUserId(auth.getUserId());
        return ResponseEntity.ok().body(new Check(isAbleSignUpUserId));
    }

    @PostMapping("/check/email")
    public ResponseEntity<Check> checkEmail(@RequestBody UserInfo userInfo) {
        Boolean isAbleSignUpEmail = userService.isAbleSignUpEmail(userInfo.getEmail());
        return ResponseEntity.ok().body(new Check(isAbleSignUpEmail));
    }

    // TODO 추후 JWT 의 ID 확인 필요
    @PostMapping("/check/password")
    public ResponseEntity<Check> checkPassword(@RequestBody Auth auth) {
        Boolean isCorrectPassword = userService.signIn(auth);
        return ResponseEntity.ok().body(new Check(isCorrectPassword));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserInfo> getUserInfo(@PathVariable("userId") String userId) {
        UserInfo userInfo = userService.findUserInfo(userId);
        return ResponseEntity.ok().body(userInfo);
    }

    @PutMapping
    public ResponseEntity modifyUserInfo(@RequestBody UserInfo userInfo) {
        userService.modifyUserInfo(userInfo);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/change/password")
    public ResponseEntity modifyPassword(@RequestBody Auth auth) {
        userService.modifyPassword(auth);
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
