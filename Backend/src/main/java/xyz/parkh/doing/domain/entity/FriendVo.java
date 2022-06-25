package xyz.parkh.doing.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
// REQUESTER_ID, ADDRESSEE_ID, STATUS
public class FriendVo {
    private String requesterId;
    private String addresseeId;
    private String status;

    public FriendVo(String requesterId, String addresseeId, String status) {
        this.requesterId = requesterId;
        this.addresseeId = addresseeId;
        this.status = status;
    }
}
