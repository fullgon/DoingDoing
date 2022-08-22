package xyz.parkh.doing.domain.user.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSimpleInfo {
    private String authId;
    private String name;
    private String email;
    private String company;

    @Builder
    public UserSimpleInfo(String authId, String name, String email, String company) {
        this.authId = authId;
        this.name = name;
        this.email = email;
        this.company = company;
    }
}
