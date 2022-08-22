package xyz.parkh.doing.api.model.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.parkh.doing.domain.user.model.UserDetailInfo;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequest {
    private Long id;
    private String authId;
    private String password;
    private String name;
    private String email;
    private String company;

    @Builder
    public SignUpRequest(String authId, String password, String name, String email, String company) {
        this.authId = authId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.company = company;
    }

    public UserDetailInfo convertToUserInfoDetail() {
        UserDetailInfo userDetailInfo = UserDetailInfo.builder().authId(authId).password(password)
                .name(name).email(email).company(company).build();
        return userDetailInfo;
    }
}
