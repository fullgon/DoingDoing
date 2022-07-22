package xyz.parkh.doing.domain.entity;

import lombok.*;
import xyz.parkh.doing.domain.model.Auth;
import xyz.parkh.doing.domain.model.UserDetailInfo;
import xyz.parkh.doing.domain.model.UserInfo;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_no")
    private Long no;

    @Column(name = "user_id", length = 20)
    private String userId; // friendId, userId 구분을 위해

    @Column(length = 255)
    private String password;

    @Column(length = 10)
    private String name;

    @Column(length = 50)
    private String email;

    @Column(length = 50)
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

    public UserDetailInfo convertToUser() {
        UserDetailInfo userDetailInfo = UserDetailInfo.builder().no(no).userId(userId).password(password)
                .name(name).email(email).company(company).build();
        return userDetailInfo;
    }

    public UserInfo convertToUserInfo() {
        UserInfo userInfo = UserInfo.builder().userId(userId).name(name)
                .email(email).company(company).build();
        return userInfo;
    }

    public void modifyPassword(Auth auth) {
        System.out.println("auth = " + auth.getPassword());
        if (userId.equals(auth.getUserId())) {
            password = auth.getPassword();
        }
        System.out.println("password = " + password);
    }

    public void modifyUserInfo(UserInfo userInfo) {
        if (userInfo.getCompany() != null) {
            this.company = userInfo.getCompany();
        }
        if (userInfo.getName() != null) {
            this.name = userInfo.getName();
        }
        if (userInfo.getEmail() != null) {
            this.email = userInfo.getEmail();
        }
    }
}

