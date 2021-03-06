package xyz.parkh.doing.domain.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ScheduleStatusDto {
    private Boolean isComplete; // 완료 됐는가?
    private Boolean hasDeadLine; // 마감 기한이 있는가?

    public ScheduleStatusDto(Boolean isComplete, Boolean hasDeadLine) {
        this.isComplete = isComplete;
        this.hasDeadLine = hasDeadLine;
    }
}
