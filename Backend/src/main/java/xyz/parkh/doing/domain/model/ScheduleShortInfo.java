package xyz.parkh.doing.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// isPublic 로 front 에서 값을 거르는게 아니라
// 글을 조회하는 사람이 권한이 있으면 그걸 주는게 더 낫지 않나
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleShortInfo {

    private int no;
    private String title;
    private boolean isPublic;
}
