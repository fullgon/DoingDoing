### 메서드 명명 규칙

Service

```
find~
add~
modify~
remove~
save~
```

Controller

```
~List()
~Details()
~Save()
~Add()
~Modify()
~Remove()
```

Mapper

```
select~
insert~
update~
delete~
```

구조

서비스에서는 예외처리, 트랜잭션 처리를 한다.

컨트롤러는 서비스를 호출하기만 한다.

메서드는 한가지의 기능만 가져야 한다.

https://cocobi.tistory.com/27

## 진행 현황

예정 : Swagger 적용 / Test 코드 작성 / 친구 기능 추가

2022.06.01. 일정 관련 버그 수정(잘 못 된 로직, DB 버전, Mapper)

2022.05.31. 기능 구현 완료 1차.

2022.05.29. 기능 구현 (일정 관련)

2022.05.27. 기능 구현 (이메일)

2022.05.~~. 구조 개선, 서버 배포

2022.05.23. JWT 적용

2022.05.21. Mapper 테스트

2022.05.16. 기능 정의, 화면 흐름 정의

2022.04.26. 프로젝트 시작

## 디버깅

### PUT, PATCH 시 form 데이터 못 읽어오는 문제

2022.05.24.

* 문제

  POST 로는 정상적으로 작동하는데

  PUT, PATCH 시 form 데이터 못 읽어오는 문제 발생

* 해결

  브라우저가 GET, POST 요청을 통해 양식 제출할 수 있지만

  브라우저가 아닌 클라이언트로 PUT, PATCH, DELETE 요청으로 양식 제출 할 수 있음

  Servlet API 에서는 HTTP POST 에 대해서만 ServletRequest.getParameter*() 를 제공함

  spring-web 이 제공하는 FormContentFilter 이용하면 PUT, PATCH, DELETE 이용해서

  application/x-www-form-urlencoded 양식 제출 할 수 있음

* 다음

  응답만 json 으로 하지 말고 요청도 json 으로 바꾸자.

```
<filter>
    <filter-name>putMapping</filter-name>
    <filter-class>org.springframework.web.filter.FormContentFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>putMapping</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

검색어 : spring putmapping null

참고 사이트

http://hwannnn.blogspot.com/2018/07/putdeletemapping-body.html

https://finkle.tistory.com/94

https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#filters-http-put

https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-web-handler-api

### 이메일 관련

2022.05.24.

현재 비밀번호 찾기 할때 아이디, 이메일 입력해 인증번호 전송

이메일에 제한을 두지 않으면 다른 사람들 아이디도 다 바꿀 수 있음

1. 기존 로직 유지하며 서버에 등록된 이메일로만 인증 가능하게?

그러면 중복 가입 방지를 위해 가입시에도 이메일 인증을 한번 해야할 듯.

Email 속성에도 인덱스 걸어 두기?

or

userId, email 을 기본키로? -> 그럼 userId 중복되는 경우 생겨서 안 될 듯

2. Email을 아이디로 사용하기엔 url 설계나 코드 작성이 id 위주로 해서

많이 엎어야 되긴 하지만 이메일로 바꾸는거에 그만큼의 장점이 있다면 고칠 의향 있음

### 요청으로 LocalDateTime 받기

2022.05.25.

직렬화?

```
// jwt, title, content, isPublic, endTime
// 일정 정보 등록
@PostMapping("/{userId}")
public Map<String, Object> postByUserIdByScheduleNo(@PathVariable("userId") String userId,
                                                    @RequestBody ScheduleVo scheduleVo,
                                                    HttpServletRequest request) {
    String userIdInJwt = (String) request.getAttribute("userId");

    return scheduleService.createSchedule(userIdInJwt, scheduleVo);
}
```

```
@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
private LocalDateTime endTime;
```

똑같이 해도 오류 발생

-> 버전 변경 후 해결

```
<dependency>
<groupId>com.fasterxml.jackson.datatype</groupId>
<artifactId>jackson-datatype-jsr310</artifactId>
<version>2.9.7</version>
</dependency>
```

참고 자료

https://jojoldu.tistory.com/361

### JWT 유지 시간

2022.05.~

Date exp = new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 21));

의도는 21일 이나 실제로는 3일이고

Date exp = new Date(System.currentTimeMillis() + (1000 * 60L * 60L * 24L * 21));

이렇게 해야한다고 하심?

의문이 들어서 검증해봄

```
Date exp1 = new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 21));
Date exp2 = new Date(System.currentTimeMillis() + (1000 * 60L * 60L * 24L * 21));

System.out.println("exp1 = " + exp1);
System.out.println("exp2 = " + exp2);

long long1 = (1000 * 60 * 60 * 24 * 21);
long long2 = (1000 * 60L * 60L * 24L * 21);

System.out.println("long1 = " + long1);
System.out.println("long2 = " + long2);
```

```
exp1 = Wed Jun 15 21:34:56 KST 2022
exp2 = Wed Jun 15 21:34:56 KST 2022
long1 = 1814400000
long2 = 1814400000
```

같은데?

작은거랑 큰거 연산하면 큰거로 가지 않나?

정해주지 않으면 기본이 그거 맞음.

[참고](http://www.tcpschool.com/java/java_datatype_typeConversion)

직접 토큰 발급해서 만료기간 비교해본 결과 차이 없음.

환경에 따라 그런일이 생길 수 도 있나...

혹시 다음에 그런 일이 생기면 한 번 시도해 보기

## Wrapper 클래스

2022.05.31.

현재 Builder 패턴을 사용 중.

불리언 타입의 값을 추가하지 않았는데 false 가 자동으로 들어가

null 을 만들어서 넣어줬더니 에러 발생

Boolean 이 아니라 boolean 사용해서 그랬음

Wrapper 가 처음엔 굳이? 였는데 왜 쓰는지 드디어 알았다.

## 테스트 코드

2022.05.31.

빠르게 기능 구현을 해보고 싶어서 테스트 코드를 생략했으나.

점점 쌓아갈 수록 불안정했음.

구조 변경시마다 PostMan 으로 테스트 해보고 안 하고 넘어간 부분도 있음

Mapper Test 를 근거로 여기까지는 이상 없다. 라는 믿음에 그 이후만 수정하면 됐음

-> 처음 시간은 더 걸릴 수 있어도 다 합쳐 보면 오히려 효율적이고 더 안정적인 작업을 할 수 있었음

## org.apache.ibatis.exceptions.PersistenceException

2022.05.31.

로컬 DB 는 에러 안나는데 AWS DB 에 연결하니까 에러 발생

이거 부터 잡자

해결

2022.06.01.

AWS 는 5.7.38-0ubuntu0.18.04.1

로컬은 8.0.26 사용 중이 었어서 생긴 오류

5.7~ utf-8 을 지원 안 함

DB 재설치 말고 업데이트 해 봄

https://tastethelinux.com/upgrade-mysql-server-from-5-7-to-8-ubuntu-18-04/

https://eehoeskrap.tistory.com/454

## 로직 수정

로직이 잘못 되어 있었음

처음엔 생각나는대로 짜다가 머리 속에서 꼬였다.

공개, 비공개 / 기한 지난 것, 기한 안 지난 것 / 기한 있는 것 ,기한 없는 것 / 완료 한 것, 완료 안 한 것

이렇게 구분했어야 했다.

뇌정지가 와서 하던 코딩을 멈추고 기준을 정했다.

지금 내가 만들려고 하는 기능은?

    목록 조회
    1. 완료 O
    2. 완료 X, 기한 O ( 기한 지난 것, 안 지난 것 구분)
    3. 완료 X, 기한 X

하고 순서대로 짜내려가니까 금방 짰다.

알고리즘 문제 풀 듯이 짜야겠다.

