package xyz.parkh.doing.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleStatusDto {
    private Boolean isComplete;
    private Boolean isTimeOut;
}
