package xyz.parkh.doing.domain.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ErrorDto {
    private String error;

    public ErrorDto(String error) {
        this.error = error;
    }
}
