package xyz.parkh.doing.domain.user.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.parkh.doing.domain.user.entity.User;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Auth {
    private String authId;
    private String password;

    public Auth(String authId, String password) {
        this.authId = authId;
        this.password = password;
    }
}
