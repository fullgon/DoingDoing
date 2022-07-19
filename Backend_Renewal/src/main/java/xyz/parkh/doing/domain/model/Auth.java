package xyz.parkh.doing.domain.model;

import lombok.Getter;
import xyz.parkh.doing.domain.entity.UserEntity;

@Getter
public class Auth {
    private String userId;
    private String password;

    public UserEntity convertToUserEntity() {
        return UserEntity.builder()
                .userId(userId).password(password).build();
    }
}
