package xyz.parkh.doing.domain.model;

import lombok.Getter;
import xyz.parkh.doing.domain.entity.user.Individual;

@Getter
public class Auth {
    private String userId;
    private String password;

    public Individual convertToUserEntity() {
        return Individual.builder()
                .userId(userId).password(password).build();
    }
}
