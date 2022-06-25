package xyz.parkh.doing.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
// USER_ID, PASSWORD
public class AuthVo {
    private String userId;
    private String password;

    public AuthVo(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
