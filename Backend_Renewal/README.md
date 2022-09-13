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

주차 구하기

```
public static Period getWeek(LocalDate date) {
    int dayOfWeek = date.getDayOfWeek().getValue();
    LocalDate thursdayOfDate = date.minusDays(dayOfWeek - 4);

    LocalDate firstThursdayOfDate = null;
    for (int i = 1; i <= 7; i++) {
        LocalDate sequentialDate = thursdayOfDate.withDayOfMonth(i);
        if (sequentialDate.getDayOfWeek() == DayOfWeek.THURSDAY) {
            firstThursdayOfDate = sequentialDate;
            break;
        }
    }

    int year = thursdayOfDate.getYear();
    int month = thursdayOfDate.getMonth().getValue();
    int week = (thursdayOfDate.getDayOfMonth() - firstThursdayOfDate.getDayOfMonth()) / 7 + 1;

    return Period.builder().year(year).month(month).week(week)
            .periodType(PeriodType.WEEK).build();
}
```

## API

### 일정 전체 목록 조회

* /api/schedules/authIdA/2022-09-07

```
{
    "userInJwt":"authIdB"
}
```

### 일정 정보 등록

* /api/schedules/authIdA

```
{
    "title": "완료 O",
    "openScope":"PUBLIC",
    "period":{
        "year":2022,
        "month":9,
        "day":7,
        "periodType":"DAY"
    },
    "scheduleType":"TODO",
    "isCompleted": true
}
```

## 매개변수

2022.09.14. 

메서드가 많아질수록 꼬임,

어떤 메서드는 엔티티를 받고, 어떤 메서드는 String 을 받음

Controller 를 오직 service 호출용으로만 사용하려 했으나,

편의를 위해 controller 에서 findBy~ 하기로 결정

AS-IS

```
Controller 에서 호출하는 Service 는 String

Serviece 에서 호출하는 Service 는 Entity
```

TO-BE

```
컨트롤러에서 findBy~ 후 서비스에 엔티티 넘겨줌
```

