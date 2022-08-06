package xyz.parkh.doing.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfo {
    private String authId;
    private String name;
    private String email;
    private String company;

    @Builder
    public UserInfo(String authId, String name, String email, String company) {
        this.authId = authId;
        this.name = name;
        this.email = email;
        this.company = company;
    }
}
