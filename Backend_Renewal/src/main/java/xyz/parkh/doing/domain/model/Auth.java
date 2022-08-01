package xyz.parkh.doing.domain.model;

import lombok.Getter;
import xyz.parkh.doing.domain.entity.user.IndividualUser;

@Getter
public class Auth {
    private String userId;
    private String password;

    public IndividualUser convertToIndividualUser() {
        return IndividualUser.builder()
                .userId(userId).password(password).build();
    }
}
