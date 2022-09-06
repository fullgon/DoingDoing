package xyz.parkh.doing.api.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.parkh.doing.domain.schedule.model.*;

import java.time.LocalDate;


@Getter
@Setter
@ToString
public class GetScheduleRequest {
    private String targetId;

    public GetScheduleRequest(String targetId) {
        this.targetId = targetId;
    }
}
