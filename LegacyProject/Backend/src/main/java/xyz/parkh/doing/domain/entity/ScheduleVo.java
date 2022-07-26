package xyz.parkh.doing.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
// NO, USER_ID, TITLE, CONTENT, END_DATE, IS_PUBLIC, IS_COMPLETE
public class ScheduleVo {
    private Integer no;
    private String userId;
    private String title;
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private Boolean isPublic;
    private Boolean isComplete; // 생성 시 default : false

    public ScheduleVo(Integer no, String userId, String title, String content, LocalDate endDate, Boolean isPublic, Boolean isComplete) {
        this.no = no;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.endDate = endDate;
        this.isPublic = isPublic;
        this.isComplete = isComplete;
    }
}
