## 회의 할 것

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