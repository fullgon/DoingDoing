package xyz.parkh.doing.domain.model;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class JwtDto {
    private String jwt;

    public JwtDto(String jwt) {
        this.jwt = jwt;
    }
}
