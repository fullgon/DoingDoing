package xyz.parkh.doing.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.parkh.doing.domain.entity.user.IndividualUser;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Auth {
    private String userId;
    private String password;

    public IndividualUser convertToIndividualUser() {
        return IndividualUser.builder()
                .userId(userId).password(password).build();
    }
}
