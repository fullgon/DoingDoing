package xyz.parkh.doing.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

// isPublic 로 front 에서 값을 거르는게 아니라
// 글을 조회하는 사람이 권한이 있으면 그걸 주는게 더 낫지 않나
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ScheduleShortInfo {

    private Integer no;
    private String title;
    private Boolean overDeadLine;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public ScheduleShortInfo(Integer no, String title, Boolean overDeadLine, LocalDate endDate) {
        this.no = no;
        this.title = title;
        this.overDeadLine = overDeadLine;
        this.endDate = endDate;
    }
}
