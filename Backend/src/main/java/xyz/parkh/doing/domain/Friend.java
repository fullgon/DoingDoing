package xyz.parkh.doing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friend {
    String requesterId;
    String addresseeId;
    String status;
}
