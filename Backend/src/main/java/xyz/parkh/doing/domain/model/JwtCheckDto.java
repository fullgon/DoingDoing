package xyz.parkh.doing.domain.model;


import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class JwtCheckDto{
    private Boolean check;
    private String jwt;

    public JwtCheckDto(Boolean check, String jwt) {
        this.check = check;
        this.jwt = jwt;
    }
}
