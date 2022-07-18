package xyz.parkh.doing.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.parkh.doing.domain.entity.UserEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    private Long no;
    private String userId;
    private String password;
    private String name;
    private String email;
    private String company;

    @Builder
    public User(Long no, String userId, String password, String name, String email, String company) {
        this.no = no;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.company = company;
    }

    public UserEntity convertToUserEntity() {
        UserEntity userEntity = UserEntity.builder().no(no).userId(userId).password(password)
                .name(name).email(email).company(company).build();
        return userEntity;
    }

    public UserInfo convertToUserInfo() {
        UserInfo userInfo = UserInfo.builder().userId(userId).name(name)
                .email(email).company(company).build();
        return userInfo;
    }
}
