package xyz.parkh.doing.api.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.parkh.doing.api.model.request.SignInRequest;
import xyz.parkh.doing.api.model.request.SignUpRequest;
import xyz.parkh.doing.api.model.response.Check;
import xyz.parkh.doing.api.model.response.UserInfoResponse;
import xyz.parkh.doing.domain.user.model.Auth;
import xyz.parkh.doing.domain.user.model.UserDetailInfo;
import xyz.parkh.doing.domain.user.model.UserSimpleInfo;
import xyz.parkh.doing.domain.user.service.UserService;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody SignUpRequest signUpRequest) {
        UserDetailInfo userDetailInfo = signUpRequest.convertToUserInfoDetail();
        userService.signUp(userDetailInfo);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Check> signIn(@RequestBody SignInRequest signInRequest) {
        Auth auth = signInRequest.convertToAuth();
        Boolean isSuccess = userService.signIn(auth);
        return ResponseEntity.ok().body(new Check(isSuccess));
    }

    @PostMapping("/check/user-id")
    public ResponseEntity<Check> checkUserId(@RequestBody String userId) {
        Boolean isAbleSignUpUserId = userService.isAbleSignUpUserId(userId);
        return ResponseEntity.ok().body(new Check(isAbleSignUpUserId));
    }

    @PostMapping("/check/email")
    public ResponseEntity<Check> checkEmail(@RequestBody String email) {
        Boolean isAbleSignUpEmail = userService.isAbleSignUpEmail(email);
        return ResponseEntity.ok().body(new Check(isAbleSignUpEmail));
    }

    // TODO 추후 JWT 의 ID 확인 필요
    @PostMapping("/check/password")
    public ResponseEntity<Check> checkPassword(@RequestBody SignInRequest signInRequest) {
        Auth auth = signInRequest.convertToAuth();
        Boolean isCorrectPassword = userService.signIn(auth);
        return ResponseEntity.ok().body(new Check(isCorrectPassword));
    }

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

    @PutMapping("/change/password")
    public ResponseEntity modifyPassword(@RequestBody SignInRequest signInRequest) {
        Auth auth = signInRequest.convertToAuth();
        userService.modifyPassword(auth);
        return ResponseEntity.noContent().build();
    }

    // TODO 현재 로그인 한 사용자 만 삭제할 수 있도록 변경
    @DeleteMapping
    public ResponseEntity remove(@RequestBody String userId) {
        userService.remove(userId);
        return ResponseEntity.noContent().build();
    }
}
