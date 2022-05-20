package xyz.parkh.doing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleStatusVo {
    Boolean isComplete;
    Boolean isTimeOut;
}
