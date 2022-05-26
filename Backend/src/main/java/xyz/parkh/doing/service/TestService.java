package xyz.parkh.doing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import xyz.parkh.doing.domain.AuthVo;
import xyz.parkh.doing.exception.CustomException;
//https://veneas.tistory.com/entry/Java-%EC%BB%A4%EC%8A%A4%ED%85%80-%EC%98%88%EC%99%B8-%EB%A7%8C%EB%93%A4%EA%B8%B0Custom-Exception
@Log4j
@Service
@Transactional
@RequiredArgsConstructor
public class TestService {

    public ResponseEntity ExceptionHandlerTest(String data) {
        if ("".equals(data)) {
            throw new NullPointerException();
        }
        return ResponseEntity.ok().body(data);

    }

//    public ResponseEntity customException(String data) {
//        if ("".equals(data)) {
//            throw new NullPointerException();
//        }
//        throw new CustomException("CustomException 발생");
//    }
}
