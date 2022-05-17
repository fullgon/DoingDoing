package xyz.parkh.doing.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.parkh.doing.domain.User;
import xyz.parkh.doing.dto.JwtResponseDTO;
import xyz.parkh.doing.dto.ResponseDTO;
import xyz.parkh.doing.dto.ResultDTO;

@Log4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/signIn")
    public ResponseEntity<JwtResponseDTO> post_auth_signIn(User user) {
        log.info(user);

        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResult("ok");
        resultDTO.setMessage(user.toString());

        JwtResponseDTO jwtResponseDTO = new JwtResponseDTO();
        jwtResponseDTO.setJwt("test");
        jwtResponseDTO.setResult(resultDTO);

        return ResponseEntity.ok().body(jwtResponseDTO);
    }

    @PostMapping("/signUp")
    public ResponseEntity<ResponseDTO> post_auth_signUp(User user) {
        log.info(user);

        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResult("ok");
        resultDTO.setMessage(user.toString());

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResult(resultDTO);

        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/sendAuthkey")
    public ResponseEntity<ResponseDTO> post_auth_sendAuthkey(User user) {
        log.info(user);

        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResult("ok");
        resultDTO.setMessage(user.toString());

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResult(resultDTO);

        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/checkAuthKey")
    public ResponseEntity<JwtResponseDTO> post_auth_checkAuthKey(User user) {
        // TODO JWT 체크
        log.info(user);

        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResult("ok");
        resultDTO.setMessage(user.toString());

        JwtResponseDTO jwtResponseDTO = new JwtResponseDTO();
        jwtResponseDTO.setJwt("test");
        jwtResponseDTO.setResult(resultDTO);

        return ResponseEntity.ok().body(jwtResponseDTO);
    }

    @PatchMapping("/resetPassword")
    public ResponseEntity<ResponseDTO> post_auth_resetPassword(User user) {
        // TODO JWT 체크
        log.info(user);

        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResult("ok");
        resultDTO.setMessage(user.toString());

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResult(resultDTO);

        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/checkPassword")
    public ResponseEntity<ResponseDTO> post_auth_checkPassword(User user) {
        // TODO JWT 체크
        log.info(user);

        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResult("ok");
        resultDTO.setMessage(user.toString());

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResult(resultDTO);

        return ResponseEntity.ok().body(responseDTO);
    }
}
