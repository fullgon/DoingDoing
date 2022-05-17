package xyz.parkh.doing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    Integer no;
    String userId;
    String title;
    String content;
    LocalDateTime endTime;
    Boolean isPublic;
}
