package xyz.parkh.doing.domain.model;

import lombok.Getter;
import xyz.parkh.doing.domain.entity.User;

@Getter
public class Auth {
    private String userId;
    private String password;

    public User convertToUserEntity() {
        return User.builder()
                .userId(userId).password(password).build();
    }
}
