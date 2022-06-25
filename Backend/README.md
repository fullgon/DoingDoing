# TODO

## 진행 현황

예정 : Swagger 적용 / Test 코드 작성 / 친구 기능 추가

2022.06.21. 에러 메시지 열거형으로 관리

2022.06.09. 배포 완료.

2022.06.02~. 프론트와 통합하며 찾은 버그 수정

2022.06.01. 일정 관련 버그 수정(잘 못 된 로직, DB 버전, Mapper)

2022.05.31. 기능 구현 완료 1차.

2022.05.29. 기능 구현 (일정 관련)

2022.05.27. 기능 구현 (이메일)

2022.05.~~. 구조 개선, 서버 배포

2022.05.23. JWT 적용

2022.05.21. Mapper 테스트

2022.05.16. 기능 정의, 화면 흐름 정의

2022.05.~~. 기획

2022.04.26. 프로젝트 시작

## 자랑 하고 싶은 것

### 더 나은 구조가 뭘까 고민하며 개발 했다.

최종 구조

```
Mapper : DB 연결

Service : 비즈니스 로직

Controller : 사용자 요청 Mapping

domain : 테이블 그대로 가져오는 Vo / 반환, 파라미터, 계층간 이동 등에 필요한 DTO

exception : 예외 처리

filter : cors, put Mapping // TODO XML 에도 중복으로 있는데 이건 나중에 해결하자

interceptor : JWT
```

초기에는 mapper - Service - controller 로 구조 나누는 것이 불필요하다는 생각이 들었다.

Service 는 단순히 mapper 에 있는 것을 한 번 더 반복하고

controller 에서 모든 것을 처리했다.

카톡 오픈채팅으로 알게 된 고마운분께 피드백을 받고

서비스 예외처리, 트랜잭션을 위해 서비스에 기능을 모으고

컨트롤러는 사용자의 요청만 받는 용도로 사용하고 있다.

아래 구조를 참고해서 개발 했다.

https://github.com/tomoyane/springboot-bestpractice

### 예외 처리

ExceptionHandler 를 이용해 예외 처리를 진행했다.

try-catch 없이 이것만으로 충분한지는 확실하지 않지만

최대한 꼼꼼하게 예외를 처리했다.

예외 발생 시 Response 에 Exception 을 하도록해 추가하기 편하도록 했다.

### 메서드 컨벤션을 지키며 개발했다.

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

https://cocobi.tistory.com/27

## 기타 디버깅 & 개발 기록

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

5.7~ utf8mb4_0900_ai_ci 을 지원 안 함

DB 재설치 말고 업데이트 해 봄

https://tastethelinux.com/upgrade-mysql-server-from-5-7-to-8-ubuntu-18-04/

https://eehoeskrap.tistory.com/454

## 로직 수정

2022.06.01.

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

## 프로젝트 방향

2022.06.01.

원래는 Swagger 를 작성하려고 했다.

근데 과연 이걸 하는게 맞을까 생각이 들었다.

이제 스프링 부트, JPA 에 집중 하고 틈틈히 Test 코드 공부해야 할 것 같다.

이 프로젝트는 지금 나에게는 최선 이었지만 나중에 보면 부족한 부분이 많을 것 같다.

클린코드 읽으면서 리팩터링 할 프로젝트 하나 만들었다.

요구사항 변경 되는 거에 맞춰서 수정 하고.

영한님 강의 듣으면서 JPA 공부 제대로 해봐야겠다.

## 통합

2022.06.02.

다 한 줄 알고 학교가서 테스트 코드 한 번 짜봐야지 했는데

막상 합쳐보니 고려하지 못한 부분이 많았다...

### Get 요청에 HTTP BODY 를 담아주고 있었다.

파라미터로 변경

### 인증번호 요청, 확인

이 부분이 진짜 헷갈렸다.

* 인증번호 확인

```
1. 회원 가입 -> JWT 필요 없고 줄수없음, 인증 확인 여부만 반환

2. 비밀번호 찾기 -> JWT 필요, 줄수 있음, jwt 도 같이 반환
-> 다음 단계인 비밀번호 변경 요청시
인증된 사용자만 변경할 수 있도록 하기 위해 
jwt 반환 하도록 설계.
```

같은 url 사용 가입된 사용자가 요청한거면 JWT 반환해 주고

가입되지 않은 사용자가 요청한거면 JWT 반환 X

이렇게 결론을 내고 진행 했는데 요청도 고려해 줬어야 했다....

* 인증 번호 요청

```
1. 회원 가입 -> 요청한 정보가 서버에 없는지 확인

2. 비밀번호 찾기 -> 서버에 저장된 이메일과 아이디가 같은지 확인

```

메서드 하나에 하나의 기능이 좋지 않을까? 생각해서 URL 을 나눌까 했다.

근데 적당한 URL 이 생각나지 않기도하고

이렇게 까지 나누는거는 과한거 아닐까 라는 생각이 들었다.

그래서 요청시 Type 을 추가로 받아 구분하려고 한다.

DB tb_authkey 에 TYPE int not null 속성 추가해서

인증 요청한 종류에 맞는 응답만 가능 하도록 설정

( 회원 가입시 요청한 이메일 인증번호 가지고 type 만 바꿔서 보내면 jwt 를 받을 수 있어서 이 문제 방지하기 위함)

### org.springframework.dao.DataIntegrityViolationException

일정 내용 비어있으면 에러 발생

-> schedule content 코드에서는 선택 값인데

DB 에는 Not Null 로 되어있음

수정.

### LocalDateTime 응답

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")

    "endTime": [
    2020,
    3,
    19,
    0,
    58,
    23
    ],

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")

    "endTime": "2020-03-19T00:58:23",

### LocalDateTime -> LocalDate

2022.06.03.

프론트 요구 사항으로 변경

### 참조 관계

2022.06.03.

부모 데이터 삭제(On Delete), 수정(On Update) 시 자식 데이터도

* Cascade : 삭제

* Set null : Null 로

* Set default : 기본 값(Default) 으로 Update

* Restrict : 자식 테이블에 데이터가 있는 경우 부모 테이블의 데이터 삭제 불가

기본으로 Restrict 였음

그러면 회원 탈퇴 시 삭제 할 수 없어서

userId 를 참조하고 있는 설정 모두 Cascade 로 변경

사용자가 탈퇴 하고 저장할 만큼 의미 있는 데이터는 아니라 생각해서 같이 삭제 되도록 설정.

## 기한 없는 일정으로 수정

2022.06.08.

기한있는 일정을 기한 없는 일정으로 바꿀 경우

endDate : 0 을 보내 주는 것으로 합의.

* ScheduleMapper.xml

```
<if test='endDate != null and endDate.toString() neq "1970-01-01"'>END_DATE = #{endDate},</if>
<if test='endDate.toString() eq "1970-01-01"'>END_DATE = null,</if>
```

근데 null 이면 변경 안하게 해서...

이게 하드 코딩인가?

근데 이거 방법이 가장 좋을 듯 함

이걸 위해 mapper 에 메서드를 추가?

음..... 해도 이럴 듯.

혹시 더 좋은 방법 생각나면 수정하기.

## 일정 종료일만 업데이트 시 오류

{{domain}}/api/schedules/parkpark/282

    {
      "endDate":"2022-04-04",
      "isComplete":true
    }

-> 가능

    {
      "isComplete":true 
    }

-> "error": "처리하지 못한 에러 발생 { org.mybatis.spring.MyBatisSystemException } ExceptionHandler 추가"

쿼리 자체는 문제 없음

```
select * from tb_schedule where no = 282;

update tb_schedule
set END_DATE = null
where no = 282;

update tb_schedule
set END_DATE = '2022-01-01'
where no = 282;
```

* mybatis

```
<if test='endDate != null and endDate.toString() neq "1970-01-01"'>END_DATE = #{endDate},</if>
<if test='endDate.toString() eq "1970-01-01"'>END_DATE = null,</if>
```

endDate 가 null 이면

위 조건 X / 아래 조건 X

null.toString() 해서 오류 터진거구나

## 배포

2022.06.10.

vue + nodejs 로 게시판 만드는 전공 수업이 있다.

vue 를 빌드해서 nodejs 에 넣기만 하니까 바로 배포되는게 신기했다.

리액트도 이렇게 되나 싶어서 찾아봤는데 있는 것 같다.

혼자 삽질하면서든 생각인데 혼자 열심히 하는건 의미 없는 것 같다.

혼자만의 우물에 갇혀 아무도 원하지 않는 방향으로 열심히 하고 있는 건 아닐까 의문이 들었다.

좋은 자료, 멘토 밑에서 배우고 싶다.

지금은 그게 영한님 강의다.

뭔 일이 생길 때마다 내가 이걸 할때인가? 생각을 한 번 하고 해야겠다.

내가 지금 배포하는거 공부하는게 1순위인가?

굳. JPA, 테스트 코드가 더 중요한 듯.

[나중에 참고](https://developer.okta.com/blog/2018/07/19/simple-crud-react-and-spring-boot)

## 에러 메시지 열거형으로 관리

2022.06.21.

AS-IS

```
throw new ValueException("필수 인자가 없습니다.");
```

TO-BE

```
throw new IllegalStateException(ErrorMessage.NOREQUIREDPARAMETER.getErrorMessage());;
```

기본으로 지원하는 Exception 로 변경하고

에러 메시지를 열거형으로 관리하도록 변경

## 인증 번호 확인 수정

2022.06.22.

변경 사항

/check/auth-key

* 기존

```
{
  "check": false,
  "jwt": "eyJraWQiOiJteUtleUlkIiwiYWxnIjoiSFMyNTYifQ.eyJpc3MiOiJQQVJLSCIsImp0aSI6IjZjN2ZlYTllMDAxMDQzNWI4ZTdhMzZiNjdlYjMzMTU3IiwiaWF0IjoxNjU1ODI0MjExLCJuYmYiOjE2NTU4MjQyMTEsImV4cCI6MTY1NzYzODYxMSwidXNlcklkIjoiaHllb24ifQ.w7d3E2PNRqL3Kx4JLxlaqmH80So5CDTcZt3GBZb3ttU"
}
```

인증번호 유효기간이 지났을 경우 이렇게 잘 못 옴

* 변경

```
* // 인증 번호 유효 시간이 지났을 경우
{
  "error": "조회된 인증번호가 없습니다."
}

// 인증 번호가 틀렸을 경우
{
  "error": "인증 번호가 일치하지 않습니다."
}
```

## 롬복 어노테이션 관련 수정 - domain

2022.06.25.

* @AllArgsConstructor

멤버 변수 선언 순서에 영항을 받지 않아 의도치 않은 예외를 발생시킬 수 있음

생성자를 명시적으로 만들고, Builder 패턴 이용할 것.

* @NoArgsConstructor

기본 생성자는 접근 제어를 지정해, 의미 없는 객체 생성 막는 것을 권장

Mybatis 의 경우 객체를 생성할 때 디폴트 생성자가 필요함.

PROTECTED 로 접근 제어

기본 생성자 없이 Controller 에서 @RequestBody 로 받을 경우 HttpMessageConversionException 발생

참고 하면 좋을 글

https://jojoldu.tistory.com/407

https://velog.io/@conatuseus/RequestBody에-기본-생성자는-왜-필요한가

TO-BE

```
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthKeyVo {
    private Integer no;
    private String userId;
    private String email;
    private String authKey;
    private Integer type;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime crateTime;
}
```

AS-IS

```
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
// USER_ID, PASSWORD
public class AuthVo {
    private String userId;
    private String password;

    public AuthVo(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
```

* @Data

무분별한 setter 는 jpa 를 사용할 경우 @toString 으로 인한 순환 참조 문제를 야기 시킬 수 있으므로,

필요한 어노테이션을 선택적으로 사용하는 것이 좋음

TO-BE

```
@Data
```

AS-IS

```
@Getter
@Setter
```

+

UserVo 의 경우 userId 로 조회한 UserVo 와 email 로 조회한 UserVo 가 같은지 비교하기 위해 equals 오버라이딩 필요

어떤건 equals 있고 어떤건 equals 없고 하면 괜히 유지 보수성만 떨어 뜨리는 거 아닌가?

일단 override 했고, 추후 equals 없이 동일한 기능 하도록 생각해 볼 것.

------------------------------- TODO 여기서 부터 하면 됨 -------------------------------

* 멤버 변수 final

JPA 엔티티를 제외하고 객체의 멤버 변수는 final 로 지정해 불변하게 지정해 주는 것이 좋음

* Setter

필요한 경우에만 의미 있는 이름으로 메소드를 새로 만들어 사용하는 것이 유지 보수 차원에서 좋음

AS-IS

```
existAuthVo.setPassword(hashedPassword);
```

TO-BE

```
existAuthVo.updatePassword(hashedPassword);
```

## Exception 변경

IllegalStateException vs IllegalArgumentException

IllegalArgumentException : 넘겨 받은 매개변수가 잘못 된 값을 가지고 있을 경우

IllegalStateException : 해당 메서드가 부적절한 시기에 호출됐을 경우

필수 인자가 없는 경우는 주로 NullPointerException 을 던진다.

AS-IS

```
throw new IllegalStateException(ErrorMessage.NOREQUIREDPARAMETER.getErrorMessage());;
...
throw new IllegalStateException(ErrorMessage.DIFFRENTSERVICETYPE.getErrorMessage());
```

TO-BE

```
throw new NullPointerException(ErrorMessage.NOREQUIREDPARAMETER.getErrorMessage());
...
throw new IllegalArgumentException(ErrorMessage.DIFFRENTEMAILANDID.getErrorMessage());
```

https://jaehun2841.github.io/2019/03/10/effective-java-item72/#표준-예외를-재사용하라

https://www.inflearn.com/questions/504063