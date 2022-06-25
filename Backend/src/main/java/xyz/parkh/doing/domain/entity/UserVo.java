package xyz.parkh.doing.domain.entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
// USER_ID, NAME, EMAIL, COMPANY
public class UserVo {
    private String userId;
    private String name;
    private String email;
    private String company;

    public UserVo(String userId, String name, String email, String company) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserVo userVo = (UserVo) o;

        if (userId != null ? !userId.equals(userVo.userId) : userVo.userId != null) return false;
        if (name != null ? !name.equals(userVo.name) : userVo.name != null) return false;
        if (email != null ? !email.equals(userVo.email) : userVo.email != null) return false;
        return company != null ? company.equals(userVo.company) : userVo.company == null;
    }
}
