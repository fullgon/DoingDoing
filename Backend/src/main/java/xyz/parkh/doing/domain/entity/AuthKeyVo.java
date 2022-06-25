package xyz.parkh.doing.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class AuthKeyVo {
    private Integer no;
    private String userId;
    private String email;
    private String authKey;
    private Integer type;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime crateTime;

    public AuthKeyVo(Integer no, String userId, String email, String authKey, Integer type, LocalDateTime crateTime) {
        this.no = no;
        this.userId = userId;
        this.email = email;
        this.authKey = authKey;
        this.type = type;
        this.crateTime = crateTime;
    }
}
