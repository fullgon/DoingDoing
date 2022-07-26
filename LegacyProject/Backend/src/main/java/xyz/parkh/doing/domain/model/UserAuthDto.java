package xyz.parkh.doing.domain.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class UserAuthDto {
    private String userId;
    private String password;
    private String name;
    private String email;
    private String company;

    public UserAuthDto(String userId, String password, String name, String email, String company) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.company = company;
    }

}
