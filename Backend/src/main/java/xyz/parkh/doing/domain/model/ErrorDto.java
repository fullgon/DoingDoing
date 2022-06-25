package xyz.parkh.doing.domain.model;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ErrorDto {
    private String error;

    public ErrorDto(String error) {
        this.error = error;
    }
}
