package xyz.parkh.doing.domain.entity.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.parkh.doing.domain.entity.FriendRequest;
import xyz.parkh.doing.domain.model.Auth;
import xyz.parkh.doing.domain.model.IndividualDetailInfo;
import xyz.parkh.doing.domain.model.UserInfo;
import xyz.parkh.doing.domain.model.friend.FriendshipState;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Table(name = "USERS")
public abstract class User {

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
    private List<FriendRequest> friendRequests = new ArrayList<>();

    public User(String userId, String password, String name, String email, String company) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        // TODO LAZY 로딩으로 프록시 객체 조회 되어 비교에서 제외, 제대로 된 해결 방법 찾기
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(no, user.no) && Objects.equals(userId, user.userId) && Objects.equals(password, user.password) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(company, user.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, userId, password, name, email, company);
    }

    public IndividualDetailInfo convertToIndividualUser() {
        IndividualDetailInfo individualDetailInfo = IndividualDetailInfo.builder().no(no).userId(userId).password(password)
                .name(name).email(email).company(company).build();
        return individualDetailInfo;
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
        for (FriendRequest friendRequest : friendRequests) {
            if (friendRequest.getFriendshipState().equals(FriendshipState.ACCEPT)) {
                memberList.add(friendRequest.getRequester());
            }
        }
        return memberList;
    }

    public List<User> readSentFriendRequests() {
        List<User> memberList = new ArrayList<>();
        for (FriendRequest friendRequest : friendRequests) {
            if (friendRequest.getFriendshipState().equals(FriendshipState.REQUEST)) {
                memberList.add(friendRequest.getRequester());
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

