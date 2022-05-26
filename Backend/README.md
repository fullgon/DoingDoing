## 회의 할 것

아이디 중복 확인, 이메일 확인

### 2022.05.24. 이메일 관련

현재 비밀번호 찾기 할때 아이디, 이메일 입력해 인증번호 전송

이메일에 제한을 두지 않으면 다른 사람들 아이디도 다 바꿀 수 있음

1. 기존 로직 유지하며 서버에 등록된 이메일로만 인증 가능하게?

그러면 중복 가입 방지를 위해 가입시에도 이메일 인증을 한번 해야할 듯.

Email 속성에도 인덱스 걸어 두기?

or

userId, email 을 기본키로? -> 그럼 userId 중복되는 경우 생겨서 안 될 듯

2. Email을 아이디로 사용하기엔 url 설계나 코드 작성이 id 위주로 해서

많이 엎어야 되긴 하지만 이메일로 바꾸는거에 그만큼의 장점이 있다면 고칠 의향 있음

## Controller 메서드명 규칙

(HTTP 메서드)(서브 URI)

카멜 케이스

```
@RequestMapping("/auth")
public class AuthController {
    
    @PostMapping("/sendAuthkey")
    public Map<String, Object> postSendAuthkey(UserVo userVO) {

    }
}
```

PathVariable 인 경우 By 로 연결

```
@RequestMapping(("/schedule"))
public class ScheduleController {

    @GetMapping("/{userId}")
    public Map<String, Object> getByUserId(@PathVariable("userId") String userId, ScheduleVo scheduleVO) {
    ~~
    }
}
```

-> getByUserId

이렇게 한 이유? 나중에 API 문서 자동화 할 수 있도록

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

## JWT 유지 시간

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