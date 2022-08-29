package xyz.parkh.doing.domain.user.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.parkh.doing.domain.user.entity.User;

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

    public UserSimpleInfo convertToUserInfo() {
        UserSimpleInfo userSimpleInfo = UserSimpleInfo.builder().authId(authId).name(name)
                .email(email).company(company).build();
        return userSimpleInfo;
    }
}
