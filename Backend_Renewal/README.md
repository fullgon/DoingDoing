# DoingDoing

일정 관리 사이트

# 사용 기술

* 메인
    * Spring Boot
    * JPA
    * MySQL(예정)

* 추가
    * GitHub
    * PostMan

## 기획

* 확정 기능
    * 월간, 주간, 일간 일정 관리.
    * 친구와 일정 공유.

* 추가해 보고 싶은 기능
    * 소셜 로그인

* 추후 기능 확장 아이디어
    * 조회한 사람 확인.
    * 한 주, 하루 기준 지정.
    * 채팅 기능.
    * 팀 단위 일정(개일 일정이 아니라, 같이 수정할 수 있도록(월간, 주간, 일간))

## 기타 기억 할 것

Test 시 롤백 되지 않도록

@Rollback(value=false)

Repository

```
List<FriendApplication> findAllByTarget_AuthIdAndFriendshipState(String authId, FriendshipState friendshipState);
List<FriendApplication> findAllByRequester_AuthIdAndFriendshipState(String authId, FriendshipState friendshipState);
FriendApplication findByRequester_AuthIdAndTarget_AuthIdAndFriendshipState(String requesterAuthId, String targetAuthId, FriendshipState friendshipState);
```

```
List<FriendApplication> findAllByTargetAndFriendshipState(User target, FriendshipState friendshipState);
List<FriendApplication> findAllByRequesterAndFriendshipState(User requester, FriendshipState friendshipState);
FriendApplication findByRequesterAndTargetAndFriendshipState(User requester, User target, FriendshipState friendshipState);
```
