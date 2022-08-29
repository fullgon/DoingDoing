package xyz.parkh.doing.api.model.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.parkh.doing.domain.user.model.Auth;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignInRequest {
    private String authId;
    private String password;

    public Auth convertToAuth() {
        return new Auth(this.authId, this.password);
    }
}
