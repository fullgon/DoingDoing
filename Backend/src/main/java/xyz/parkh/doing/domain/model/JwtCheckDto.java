package xyz.parkh.doing.domain.model;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class JwtCheckDto {
    private Boolean check;
    private String jwt;

    public JwtCheckDto(Boolean check, String jwt) {
        this.check = check;
        this.jwt = jwt;
    }
}
