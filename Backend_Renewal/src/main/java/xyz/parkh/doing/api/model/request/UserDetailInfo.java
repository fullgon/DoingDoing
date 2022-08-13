package xyz.parkh.doing.api.model.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.parkh.doing.domain.entity.user.User;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDetailInfo {
    private Long id;
    private String authId;
    private String password;
    private String name;
    private String email;
    private String company;

    @Builder
    public UserDetailInfo(String authId, String password, String name, String email, String company) {
        this.authId = authId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.company = company;
    }

    public User convertToUser() {
        User user = User.builder().authId(authId).password(password)
                .name(name).email(email).company(company).build();
        return user;
    }

    public UserInfo convertToUserInfo() {
        UserInfo userInfo = UserInfo.builder().authId(authId).name(name)
                .email(email).company(company).build();
        return userInfo;
    }
}
