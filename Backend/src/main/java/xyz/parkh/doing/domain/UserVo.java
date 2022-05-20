package xyz.parkh.doing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// USER_ID, NAME, EMAIL, COMPANY
public class UserVo {
    String userId;
    String name;
    String email;
    String company;
}
