package xyz.parkh.doing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAuthVo {
    private String userId;
    private String password;
    private String name;
    private String email;
    private String company;
}
