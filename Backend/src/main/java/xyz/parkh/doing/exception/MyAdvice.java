package xyz.parkh.doing.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.parkh.doing.domain.ErrorDto;

@Slf4j
@RestControllerAdvice
public class MyAdvice {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDto> nullPointException(Exception e) {
        log.error(e.getMessage());
        ErrorDto error = ErrorDto.builder().error("필수 인자가 부족합니다.").build();

        return ResponseEntity.badRequest().body(error);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ResponseDto> customException(Exception e) {
//        ResponseDto error = ResponseDto.builder().result("customException").message(e.getMessage()).build();
//
//        return ResponseEntity.badRequest().body(error);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ResponseDto> exception(Exception e) {
//        ResponseDto error = ResponseDto.builder().result("Exception").message(e.getMessage()).build();
//
//        return ResponseEntity.badRequest().body(error);
//    }
}
