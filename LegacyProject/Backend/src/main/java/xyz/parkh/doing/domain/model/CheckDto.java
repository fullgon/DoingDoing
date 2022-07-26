package xyz.parkh.doing.domain.model;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class CheckDto {
    private Boolean check;

    public CheckDto(Boolean check) {
        this.check = check;
    }
}
