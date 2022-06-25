package xyz.parkh.doing.domain.model;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class CheckDto {
    private Boolean check;

    public CheckDto(Boolean check) {
        this.check = check;
    }
}
