package xyz.parkh.doing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// REQUESTER_ID, ADDRESSEE_ID, STATUS
public class FriendVo {
    String requesterId;
    String addresseeId;
    String status;
}
