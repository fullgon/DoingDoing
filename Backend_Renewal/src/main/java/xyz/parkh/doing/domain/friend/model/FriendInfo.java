package xyz.parkh.doing.domain.friend.model;

import lombok.Getter;

@Getter
public class FriendInfo {
    private Long id;
    private String authId;
    private String name;

    public FriendInfo(Long id, String authId, String name) {
        this.id = id;
        this.authId = authId;
        this.name = name;
    }
}
