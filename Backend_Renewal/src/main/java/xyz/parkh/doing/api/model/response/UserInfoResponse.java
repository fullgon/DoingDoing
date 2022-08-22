package xyz.parkh.doing.api.model.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.parkh.doing.domain.user.model.UserSimpleInfo;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfoResponse {
    private String authId;
    private String name;
    private String email;
    private String company;

    @Builder
    public UserInfoResponse(String authId, String name, String email, String company) {
        this.authId = authId;
        this.name = name;
        this.email = email;
        this.company = company;
    }

    public static UserInfoResponse makeFromUserInfoSimple(UserSimpleInfo userSimpleInfo) {
        return new UserInfoResponse(userSimpleInfo.getAuthId(), userSimpleInfo.getName(),
                userSimpleInfo.getEmail(), userSimpleInfo.getCompany());
    }
}
