package xyz.parkh.doing.api.model.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.parkh.doing.domain.entity.user.User;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Auth {
    private String authId;
    private String password;

    public User convertToUser() {
        return User.builder()
                .authId(authId).password(password).build();
    }
}
