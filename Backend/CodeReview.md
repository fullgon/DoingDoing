# 기술

## 에러 메시지

에러 메시지를 매번 입력 하는 것 보다 한 곳에서 관리하는 것이 유지 보수에 좋음

enum, properties

AS-IS

```
throw new ValueException("필수 인자가 없습니다.");
```

TO-BE

```
TODO enum 으로 변경
```

## 검증

모든 기능에 유저 검증 로직이 들어있는데 config 파일에 jwt 토큰 인증을 통한 필터를 추가해 공통으로 처리 가능

생성자에 필수 인자 검증 로직을 넣을 수도 있고, 스프링 validation 을 용할 수도 있음

TODO 스프링 시큐리티 공부 추천

## 테스트

실패하는 케이스에 대한 테스트 코드가 빠짐 (exception 발생)

## 예외 처리

되도록이면 표준 예외처리를 통해 예외처리하고

필요한 경우에만 custom exception 사용할 것.

AS-IS

```

```

TO-BE

```

```

# 구조

## 서비스 레이어

비즈니스 로직과 관련된 책임만 가지도록 권장

ResponseEntity 와 같이 웹과 관련된 로직이나 객체는 컨트롤러에서 그 역할을 수행하는 것이 좋을 듯.

## 메서드

하나의 메서드가 너무 많은 역할을 수행.

코드가 길어져 가독성이 떨어 질 수 있음

메소드 추출, 비슷한 역할을 수행하는 로직을 모아 객체로 분리해 역할과 책임을 나눌 것.

인자 입력의 경우 validation 메서드로 빼면 좋을 듯.

중복 체크를 메서드 혹은 객체로 분리 하는 것은 어떨까?

그리고 메서드로 뻬서 사용한다면 메서드명을 보고 어떤 처리를 하는지 메서드명을 명확하게 해줘야함

TODO 래팩토링, 틀린코드 책 추천

# 코딩 스타일

## 롬복

JPA 엔티티를 제외하고 객체의 멤버 변수는 final 로 지정해 불변하게 지정해 주는 것이 좋음

@AllArgsConstructor

멤버 변수 선언 순서에 영항을 받지 않아 의도치 않은 예외를 발생시킬 수 있음

생성자를 명시적으로 만들고, Builder 패턴 이용할 것.

기본 생성자는 접근 제어를 지정해, 의미 없는 객체 생성 막는 것을 권장

@Data

무분별한 setter 는 jpa 를 사용할 경우 @toString 으로 인한 순환 참조 문제를 야기 시킬 수 있으므로,

필요한 어노테이션을 선택적으로 사용하는 것이 좋음

https://lkhlkh23.tistory.com/159

## Setter

setter 의 경우 필요한 경우에만 의미 있는 이름으로 메소드를 새로 만들어 사용하는 것이 유지 보수 차원에서 좋음

AS-IS

```
existAuthVo.setPassword(hashedPassword);
```

TO-BE

```
existAuthVo.updatePassword(hashedPassword);
```

## List, ArrayList

뚜렷한 목적이 있는게 아니라면 컬렉션 프레임워크에서 제공하는 기본 인터페이스로 받는게 좋음 (List, Set 등)

AS-IS

```
ArrayList<ScheduleShortInfo> scheduleShortInfoList = new ArrayList<>();
```

TO-BE

```
List<ScheduleShortInfo> scheduleShortInfoList = new ArrayList<>();
```

ArrayList 는 List 의 구현체로

인터페이스로 받으면 더 유연 하게 사용할 수 있음

# 코드

## ScheduleShortInfo 객체 생성을 ScheduleVo 에 위임하면 어떨까요?

추후에 DTO, Entity 변환 할 때도 이런식으로 하면 좋을 듯.

AS-IS

```
ScheduleShortInfo scheduleShortInfo = new ScheduleShortInfo().builder().no(no)
    .endDate(endDate).overDeadLine(overDeadLine).title(title).build();
```

TO-BE

ScheduleShortInfo 객체 생성을 ScheduleVo 에 위임

```
public ScheduleShortInfo toScheduleShortInfo() {
    return ScheduleShortInfo.builder()
            .no(this.no)
            .endDate(this.endDate)
            .overDeadLine(LocalDate.now().isAfter(this.endDate))
            .title(this.title)
            .build();
}
```

## 리스트를 가공해서 새로운 리스트로 반환 시키는 로직

AS-IS

```
ScheduleShortInfo scheduleShortInfo = new ScheduleShortInfo().builder().no(no).title(title).build();
    scheduleShortInfoList.add(scheduleShortInfo);
```

TO-BE

* ScheduleService.java

TODO 아직 코드에 적용 안 함

```
    scheduleList.stream()
        .map(schedule -> getScheduleShortInfo(schedule, requestIsComplete , requestHasDeadLine))
        .collect(Collectors.toList());

    
    public ScheduleShortInfo getScheduleShortInfo(ScheduleVo schedule, boolean requestIsComplete , boolean requestHasDeadLine){
        ..........
    }
}
```

## 기타

로그는 꼭 필요한 정보만 남길 것

코드의 불필요한 주석도 제거 할 것

@Autowired 방식에 객체주입보다 생성자 방식 주입을 선호하는지 생각해보면 좋겠습니다.
(알고 계시면 넘어가셔도 됩니다)

## 추후 공부하면 좋을 것

cors, transaction,

# 질의 응답

## domain, controller 관련

* 요청과 응답을 할 때 값을 넓게 (userId 만 필요해도 기존에 있던 UserVo 로) 받았는데 딱 필요한 값만 받도록 새로 만들어 주는 게 좋을까요?

```
요청 메소드 형식에 따라 조금 다르겠지만 파라미터가 1개인 경우에는 따로 객체로 받지 않아도 상관없습니다.
```

## exception, service 관련

* 특정 상황마다 예외를 던지고 RestControllerAdvice 받는 식으로해서 예외 처리를 했는데 이렇게 해도 괜찮을까요?

```
일반적으로 많이 사용하는 방법입니다. 상황에 맞는 에러 메시지와 상태코드만 잘 내려준다면 공통으로 예외 처리를 할 수 있어 큰 이점이 있습니다.
```

* 서비스가 간단해서 그런지 service 와 controller 가 1대1 느낌으로 구성 되었습니다 service 와 controller 를 어떤 기준(기능? URL?) 으로 나누는 것이 좋을지 궁금합니다.

```
이건 정답이 없는거 같네요. 컨트롤러는 단순 요청을 받아 서비스 레이어에 필요한 파라미터를 넘겨주고 반환 받은 결과를 응답으로 보내주는 역할이 다입니다. 서비스가 하나만 필요할 수도 있고 추가 요구사항이 생겨 이전에
작성해 둔 다른 서비스를 추가하여 기존 결과에 추가한 서비스의 로직을 태울 수도 있고 한 서비스에 기능을 몰아 넣을 수도 있고 이건 개발자 성향 차이라고 생각합니다.
```

## 전체

* 프로젝트를 진행하면서 계속 수정 사항이 생기던데 이건 많이 경험 해보고 꼼꼼하게 설계하는 방법 밖에 없을까요?

```
아무리 꼼꼼하게 설계해도 요구사항은 계속 들어오고 리팩토링할 건들은 끊임없이 생깁니다. 
리팩토링 책에 보면 아직 일어날지 안 일어날지도 모를 미래를 대비해서 오버 엔지니어링 하지는 마라고 안내하고 있습니다. 
현재 최선이라고 생각이 드는 방법으로 개발을 먼저 진행하고 리팩토링하는 방향으로 가는게 좋을 것 같습니다. 
TDD 같은 방식을 사용하면 개발하면서 설계와 리팩토링이 따라오긴 하는데 숙달하는데 시간이 상당히 걸립니다.
선개발 -> 리팩토링 무한 반복 이건 개발자의 숙명이네요 ㅠ
```

* API 문서를 한글로 하자니 수정 사항 생길 때마다 수정, 공유하는 것이 번거롭고, Swagger 를 적용해보려고 했는데 코드 양이 정말 많아질 것 같습니다.

```
프로젝트 관리할 때는 API 문서가 정말 중요합니다. 
문서를 보는 대상이 백엔드 개발자 뿐만 아니라 프론트 엔드 개발자, 기획자, PM 등 다양한 직군의 사람들이 보게 됩니다. 
과거에는 스웨거만 작성하는 경우가 많았는데 요즘은 스웨거 + restdoc 조합으로 많이 사용하는 것 같습니다.
```

* 총 평

```
첨언 드리고 싶은 것은 코드 작성하실 때 대체적으로 한 메소드가 많은 책임을 지고 있어 코드량이 상당합니다. 
이것을 줄이기 위해 메소드 추출이라던가 다른 객체에 위임한다던가 디자인 패턴을 적용하여 중복을 줄인다던가
여러가지 방법들이 있는데 이런 연습이 조금 필요해 보입니다.
클린코드와 리팩토링 책을 정독해보시는 것을 추천드립니다. 
이런 개념들을 익히시고 의식하면서 개발하다 보면 자연스레 객체지향이나 디자인 패턴, TDD 같은 것들에 관심이 생길 겁니다.
마지막으로 커밋 단위가 너무 광범위 합니다. 기능 단위로 좁히는 것이 좋을 것 같습니다.
```

# 다른 분

## DTO

요청, 응답별 역할을 분리해 DTO 를 나눈 것 잘했다.

추후 유지 보수 할때 다른 개발자가 보더라도 요청별 필요한 필드, 응답별 필요한 필드를 파악하기 위워 유지보수에 용이

## 컨트롤러

url 정보는 중복되는 경우가 아니라면 따로 관리하지 안혹 그대로 노출해도 좋을 듯

컨트롤러에는 가급적 비즈니스 로직이 없는게 깔끔할 듯

웹(http) 와 연관된 로직만 남기고 서비스나 엔티티 등에 책임을 전가시키는게 좋을 듯

## 기타

매직 넘거 사용은 가급적 지양

필요할 경우 정적 변수로 따로 관리하는 것이 좋음

## 스트림

get() 메소드 사용은 지양하는 것이 좋음

필수 데이터라면 null 처리를 위해 orElseThrow() 메서드 사용 추천

## 유효성 검사

유효성 검사 로직을 모아 메소드로 분리한 것 잘 하셨습니다.

## 불변 객체

가능하면 필드는 immutable 하게 관리하도록 final 지정해 주면 좋을 듯

## 생성자 관련 어노테이션

@AllArgsConstructor 혹은 @RequiredArgsConstructor 어노테이션의 경우

동일한 타입의 필드가 두 개 이상일 경우 필드 순서를 변경할 시 문제를 야기시킬 수 있기 때문에 가능하면 사용을 지양하도록 권장하고 있습니다.

전체 필드를 주입 받는 생성자를 하나 명시적으로 만들고 정적 팩토리 메소드를 사용하거나 빌더 패턴을 이용하는 것이 좋을 것 같습니다.

# 느낀점

내가 되고 싶은 사람들을 만나야 겠다.

멘토링 덕분에 막연했던 부분들이 어느 정도 해소 되었다.

말씀해 주신 부분 참고해 리팩터링 하면서 계속 공부 해야 겠다.

친구들 취업 한다고 나도 그래야 되나 흔들리지 말고, 우선 기본을 갖추자
