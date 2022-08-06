package xyz.parkh.doing.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.parkh.doing.domain.entity.user.User;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Auth {
    private String userId;
    private String password;

    public User convertToUser() {
        return User.builder()
                .userId(userId).password(password).build();
    }
}
