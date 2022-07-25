package xyz.parkh.doing.domain.entity;

import lombok.*;
import xyz.parkh.doing.domain.model.Auth;
import xyz.parkh.doing.domain.model.UserDetailInfo;
import xyz.parkh.doing.domain.model.UserInfo;
import xyz.parkh.doing.domain.model.friend.FriendshipState;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_no")
    private Long no;

    @Column(name = "user_id", length = 20)
    private String userId; // friendId, userId 구분을 위해

    @Column(length = 255)
    private String password;

    @Column(length = 10)
    private String name;

    @Column(length = 50)
    private String email;

    @Column(length = 50)
    private String company;

    @OneToMany(mappedBy = "id")
    private List<Friendship> friendships = new ArrayList<>();

    @Builder
    public User(Long no, String userId, String password, String name, String email, String company) {
        this.no = no;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.company = company;
    }

    public UserDetailInfo convertToUser() {
        UserDetailInfo userDetailInfo = UserDetailInfo.builder().no(no).userId(userId).password(password)
                .name(name).email(email).company(company).build();
        return userDetailInfo;
    }

    public UserInfo convertToUserInfo() {
        UserInfo userInfo = UserInfo.builder().userId(userId).name(name)
                .email(email).company(company).build();
        return userInfo;
    }

    public void modifyPassword(Auth auth) {
        System.out.println("auth = " + auth.getPassword());
        if (userId.equals(auth.getUserId())) {
            password = auth.getPassword();
        }
        System.out.println("password = " + password);
    }

    public void modifyUserInfo(UserInfo userInfo) {
        if (userInfo.getCompany() != null) {
            this.company = userInfo.getCompany();
        }
        if (userInfo.getName() != null) {
            this.name = userInfo.getName();
        }
        if (userInfo.getEmail() != null) {
            this.email = userInfo.getEmail();
        }
    }

    /*
    * 외래키가 Friendship 에 있기 때문에 객체 지향적인 것을 포기하더라도,
    * Friendship 에서 친구를 관리하는 것이 좋지만,
    * 조회 정도는 자주 할 것 같다고 판단해 양방향 매핑 추가.
    * */
     public List<User> readFriends() {
        List<User> memberList = new ArrayList<>();
        for (Friendship friendship : friendships) {
            if (friendship.getFriendshipState().equals(FriendshipState.ACCEPT)) {
                memberList.add(friendship.getRequester());
            }
        }
        return memberList;
    }

    public List<User> readSentFriendRequests() {
        List<User> memberList = new ArrayList<>();
        for (Friendship friendship : friendships) {
            if (friendship.getFriendshipState().equals(FriendshipState.REQUEST)) {
                memberList.add(friendship.getRequester());
            }
        }
        return memberList;
    }

// TODO
//  가능 하다면 친구 요청 들어온 목록 확인 하는 메서드 생성
//  불가능 할 경우 readSentFriendRequests 도 삭제
//  JPQL 학습 후
//  Repository 에서 조회 하는게 나을지
//  여기서 조회 하는게 나을지 생각 해보기
//    public List<User> readReceivedFriendRequests() {}

}

