package xyz.parkh.doing.domain.friend.model;

import lombok.Getter;

@Getter
public class FriendInfo {
    private Long friendApplicationId;
    private String authId;
    private String name;

    public FriendInfo(Long friendApplicationId, String authId, String name) {
        this.friendApplicationId = friendApplicationId;
        this.authId = authId;
        this.name = name;
    }
}
