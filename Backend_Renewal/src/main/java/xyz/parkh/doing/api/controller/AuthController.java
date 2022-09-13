package xyz.parkh.doing.api.controller;


import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.parkh.doing.api.model.request.SignInRequest;
import xyz.parkh.doing.domain.user.model.Auth;
import xyz.parkh.doing.domain.user.model.UserDetailInfo;
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

    @Getter
    static class SignUpRequest {
        private String authId;
        private String password;
        private String name;
        private String email;
        private String company;

        public UserDetailInfo convertToUserInfoDetail() {
            return new UserDetailInfo(authId, password, name, email, company);
        }
    }


    @PostMapping("/sign-in")
    public ResponseEntity<signInResponse> signIn(@RequestBody SignInRequest signInRequest) {
        Auth auth = signInRequest.convertToAuth();
        Boolean isSuccess = userService.signIn(auth);
        String token = null;
        if (isSuccess) {
            token = tokenProvider.create(auth.getAuthId());
        }
        return ResponseEntity.ok().body(new signInResponse(isSuccess, token));
    }

    @Getter
    static class signInResponse {
        private boolean isSuccess;
        private String token;

        public signInResponse(boolean isSuccess, String token) {
            this.isSuccess = isSuccess;
            this.token = token;
        }
    }

    @PostMapping("/check/user-id")
    public ResponseEntity<CheckBasicResponse> checkUserId(@RequestBody String userId) {
        Boolean isAbleSignUpUserId = userService.isAbleSignUpUserId(userId);
        return ResponseEntity.ok().body(new CheckBasicResponse(isAbleSignUpUserId));
    }

    @PostMapping("/check/email")
    public ResponseEntity<CheckBasicResponse> checkEmail(@RequestBody String email) {
        Boolean isAbleSignUpEmail = userService.isAbleSignUpEmail(email);
        return ResponseEntity.ok().body(new CheckBasicResponse(isAbleSignUpEmail));
    }

    @Getter
    static class CheckBasicResponse {
        private Boolean check;

        public CheckBasicResponse(Boolean check) {
            this.check = check;
        }
    }

    // TODO 추후 JWT 의 ID 확인 필요
    @PostMapping("/check/password")
    public ResponseEntity<CheckJwtResponse> checkPassword(@RequestBody SignInRequest signInRequest) {
        Auth auth = signInRequest.convertToAuth();
        Boolean isCorrectPassword = userService.signIn(auth);

        String token = null;
        if (isCorrectPassword) {
            token = tokenProvider.create(auth.getAuthId());
        }
        // 가능 하다면 기존 토큰 삭제
        return ResponseEntity.ok().body(new CheckJwtResponse(isCorrectPassword, token));
    }

    @Getter
    static class CheckJwtResponse {
        private Boolean check;
        private String token;

        public CheckJwtResponse(Boolean check, String token) {
            this.check = check;
            this.token = token;
        }
    }

    @PutMapping("/change/password")
    public ResponseEntity modifyPassword(@RequestBody SignInRequest signInRequest) {
        Auth auth = signInRequest.convertToAuth();
        userService.modifyPassword(auth);
        return ResponseEntity.noContent().build();
    }

}
