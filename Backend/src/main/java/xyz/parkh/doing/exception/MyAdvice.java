package xyz.parkh.doing.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.parkh.doing.domain.ResponseDto;

@RestControllerAdvice
public class MyAdvice {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ResponseDto> nullPointException(Exception e) {
        ResponseDto error = ResponseDto.builder().result("NullPointException").message(e.getMessage()).build();

        return ResponseEntity.badRequest().body(error);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ResponseDto> customException(Exception e) {
//        ResponseDto error = ResponseDto.builder().result("customException").message(e.getMessage()).build();
//
//        return ResponseEntity.badRequest().body(error);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> exception(Exception e) {
        ResponseDto error = ResponseDto.builder().result("Exception").message(e.getMessage()).build();

        return ResponseEntity.badRequest().body(error);
    }
}
