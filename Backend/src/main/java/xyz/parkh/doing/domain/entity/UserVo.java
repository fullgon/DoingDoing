package xyz.parkh.doing.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
}
