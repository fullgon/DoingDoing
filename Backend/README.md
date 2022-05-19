Controller 메서드명 규칙

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