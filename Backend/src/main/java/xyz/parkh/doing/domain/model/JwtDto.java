package xyz.parkh.doing.domain.model;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class JwtDto {
    private String jwt;

    public JwtDto(String jwt) {
        this.jwt = jwt;
    }
}
