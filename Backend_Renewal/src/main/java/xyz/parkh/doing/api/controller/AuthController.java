package xyz.parkh.doing.api.controller;


import lombok.Getter;
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
import xyz.parkh.doing.security.TokenProvider;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    private final TokenProvider tokenProvider;

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody SignUpRequest signUpRequest) {
        UserDetailInfo userDetailInfo = signUpRequest.convertToUserInfoDetail();
        userService.signUp(userDetailInfo);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<signIdResponse> signIn(@RequestBody SignInRequest signInRequest) {
        Auth auth = signInRequest.convertToAuth();
        Boolean isSuccess = userService.signIn(auth);
        String token = null;
        if (isSuccess) {
            token = tokenProvider.create(auth.getAuthId());
        }
        return ResponseEntity.ok().body(new signIdResponse(isSuccess, token));
    }

    @Getter
    static class signIdResponse {
        private boolean isSuccess;
        private String token;

        public signIdResponse(boolean isSuccess, String token) {
            this.isSuccess = isSuccess;
            this.token = token;
        }
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

    @PutMapping("/change/password")
    public ResponseEntity modifyPassword(@RequestBody SignInRequest signInRequest) {
        Auth auth = signInRequest.convertToAuth();
        userService.modifyPassword(auth);
        return ResponseEntity.noContent().build();
    }

}
