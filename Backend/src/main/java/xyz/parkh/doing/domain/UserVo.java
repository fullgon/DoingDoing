package xyz.parkh.doing.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
// USER_ID, NAME, EMAIL, COMPANY
public class UserVo {
    private String userId;
    private String name;
    private String email;
    private String company;
}
