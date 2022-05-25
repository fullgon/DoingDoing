package xyz.parkh.doing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// NO, USER_ID, TITLE, CONTENT, END_TIME, IS_PUBLIC
public class ScheduleVo {
    private Integer no;
    private String userId;
    private String title;
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;

    private Boolean isPublic;
}
