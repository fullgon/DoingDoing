package xyz.parkh.campus.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import xyz.parkh.campus.dto.ResponseDTO;
import xyz.parkh.campus.dto.UserDTO;
import xyz.parkh.campus.entity.User;
import xyz.parkh.campus.security.TokenProvider;
import xyz.parkh.campus.service.UserService;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenProvider tokenProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/signUp")
    public ResponseEntity<?> post_account_signUp(@RequestBody UserDTO userDTO) {
        try {
//			요청을 이용해 저장할 사용자 생성
            User user = User.builder()
                    .userId(userDTO.getUserId())
                    .email(userDTO.getEmail())
                    .name(userDTO.getName())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .build();

//			서비스를 이용해 오류 처리 및 리포지토리에 사용자 저장
            User registeredUser = userService.create(user);
            log.info("sign up user = " + user.getName());

//			반환을 위해 UserDTO 생성
            UserDTO responseUserDTO = userDTO.builder()
                    .email(registeredUser.getEmail())
                    .userId(registeredUser.getUserId())
                    .name(registeredUser.getName())
                    .build();

            return ResponseEntity.ok().body(responseUserDTO);
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.internalServerError().body(responseDTO);
        }
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> post_account_signIn(@RequestBody UserDTO userDTO) {
        try {
            User user = userService.getByCredentials(userDTO.getUserId(), userDTO.getPassword(), passwordEncoder);

            if (user != null) {
                // 토큰 생성
                final String token = tokenProvider.create(user);

                final UserDTO responseUserDTO = userDTO.builder()
                        .userId(user.getUserId())
                        .token(token)
                        .build();
                log.info("sign in user = " + user.getName());
                return ResponseEntity.ok().body(responseUserDTO);
            } else {
                ResponseDTO responseDTO = ResponseDTO.builder()
                        .error("Login failed").build();
                return ResponseEntity.badRequest().body(responseDTO);
            }
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .error(e.getLocalizedMessage()).build();
            return ResponseEntity.internalServerError().body(responseDTO);
        }
    }

    @PostMapping("/sendAuthKey") // email
    public ResponseEntity<?> post_account_sendAuthKey(@RequestBody UserDTO userDTO) {
        if (userDTO.getEmail() != null) {
            final UserDTO responseUserDTO = userDTO.builder()
                    .email(userDTO.getEmail()).build();
            return ResponseEntity.ok().body(responseUserDTO);
        } else {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .error("Please Check Email").build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PostMapping("/checkAuthKey") // authKey
    public ResponseEntity<?> post_account_checkAuthKey(@RequestBody String authKey) {
        // TODO 일단 파라미터로 authKey 받아서 반환 하도록 제작
        try {
            System.out.println(authKey);
            if (authKey != null) {
                ArrayList<String> list = new ArrayList<>();
                list.add(authKey);
                final ResponseDTO<String> responseDTO = ResponseDTO.<String>builder()
                        .data(list).build();
                return ResponseEntity.ok().body(responseDTO);
            } else {
                ResponseDTO responseDTO = ResponseDTO.builder()
                        .error("Please Check AuthKey").build();
                return ResponseEntity.badRequest().body(responseDTO);
            }
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .error(e.getLocalizedMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PatchMapping("/resetPassword") // id, password
    public ResponseEntity<?> patch_account_resetPassword(@RequestBody UserDTO userDTO) {
        try {
            if (userDTO.getPassword() != null || userDTO.getUserId() != null) {
                // TODO : 임시로 넣어둔 값, 제대로 값이 받아지나 확인하기 위해 DTO 에 id, password 받아 반환
                final UserDTO responseUserDTO = userDTO.builder().userId(userDTO.getUserId()).password(userDTO.getPassword()).build();
                return ResponseEntity.ok().body(responseUserDTO);
            } else {
                ResponseDTO responseDTO = ResponseDTO.builder()
                        .error("Please Check Password").build();
                return ResponseEntity.badRequest().body(responseDTO);
            }
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .error(e.getLocalizedMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

}
