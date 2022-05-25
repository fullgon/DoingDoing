package xyz.parkh.doing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// NO, USER_ID, EMAIL, AUTHKEY, CREATE_TIME
public class AuthKeyVo {
    private Integer no;
    private String userId;
    private String email;
    private String authKey;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime crateTime;
}
