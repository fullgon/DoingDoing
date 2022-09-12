package xyz.parkh.doing.api.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.parkh.doing.api.model.request.SignInRequest;
import xyz.parkh.doing.api.model.response.UserInfoResponse;
import xyz.parkh.doing.domain.user.model.Auth;
import xyz.parkh.doing.domain.user.model.UserSimpleInfo;
import xyz.parkh.doing.domain.user.service.UserService;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoResponse> getUserInfo(@PathVariable("userId") String userId) {
        UserSimpleInfo userSimpleInfo = userService.findUser(userId);
        UserInfoResponse userInfoResponse = UserInfoResponse.makeFromUserInfoSimple(userSimpleInfo);
        return ResponseEntity.ok().body(userInfoResponse);
    }

    @PutMapping
    public ResponseEntity modifyUserInfo(@RequestBody UserSimpleInfo userInfo) {
        userService.modifyUser(userInfo);
        return ResponseEntity.noContent().build();
    }

    // TODO 현재 로그인 한 사용자 만 삭제할 수 있도록 변경
    @DeleteMapping
    public ResponseEntity remove(@RequestBody String userId) {
        userService.remove(userId);
        return ResponseEntity.noContent().build();
    }
}
