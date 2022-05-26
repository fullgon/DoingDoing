/**
 * 로그인, 회원가입, 사용자 확인 등
 * 사용자 인증 관련된 요청을 받는 Controller
 */

package xyz.parkh.doing.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.parkh.doing.service.TestService;

@Log4j
@RestController
@RequestMapping("/api/exception")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @ResponseStatus(HttpStatus.BAD_GATEWAY) // 상태 코드만 이렇게 던지고 return 은 제대로 return 함, 근데 이 메서드는 항상 같은 상태 리턴..
    @PostMapping("/test")
    public ResponseEntity postSignIn(@RequestBody String data) {
        return testService.ExceptionHandlerTest(data);
    }

//    @ResponseStatus(HttpStatus.BAD_GATEWAY) // 상태 코드만 이렇게 던지고 return 은 제대로 return 함, 근데 이 메서드는 항상 같은 상태 리턴..
//    @PostMapping("/null")
//    public ResponseEntity postSignIn(@RequestBody String data) {
//        return testService.nullPointException(data);
//    }
//
//    @ResponseStatus(HttpStatus.BAD_GATEWAY) // 상태 코드만 이렇게 던지고 return 은 제대로 return 함, 근데 이 메서드는 항상 같은 상태 리턴..
//    @PostMapping("/custom")
//    public ResponseEntity postSignIn(@RequestBody String data) {
//        return testService.nullPointException(data);
//    }






}
