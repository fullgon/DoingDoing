package xyz.parkh.doing.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class RequestAuthKeyVo {
    private String userId;
    private String email;
    private Integer type; // 00 : 비밀번호 찾기, 01 : 회원 가입

    public RequestAuthKeyVo(String userId, String email, Integer type) {
        this.userId = userId;
        this.email = email;
        this.type = type;
    }
}
