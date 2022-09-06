package xyz.parkh.doing.api.model.request;

import xyz.parkh.doing.domain.schedule.model.OpenScope;
import xyz.parkh.doing.domain.schedule.model.Period;
import xyz.parkh.doing.domain.schedule.model.ScheduleType;

public class AddScheduleRequest {
    private String authId;
    private String title;
    private OpenScope openScope;
    private Period period;
    private Boolean isCompleted;
    private ScheduleType scheduleType;



}
