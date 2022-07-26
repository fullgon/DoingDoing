package xyz.parkh.doing.domain.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ResponseDto {
    private String result;

    public ResponseDto(String result) {
        this.result = result;
    }
}
