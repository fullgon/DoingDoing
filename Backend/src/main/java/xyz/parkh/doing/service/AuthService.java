package xyz.parkh.doing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.*;
import xyz.parkh.doing.exception.DifferentAuthException;
import xyz.parkh.doing.exception.ExistsException;
import xyz.parkh.doing.exception.ValueNullException;
import xyz.parkh.doing.interceptor.JwtManager;
import xyz.parkh.doing.mapper.AuthKeyMapper;
import xyz.parkh.doing.mapper.AuthMapper;
import xyz.parkh.doing.mapper.UserMapper;
import xyz.parkh.doing.service.email.EmailService;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserMapper userMapper;
    private final AuthMapper authMapper;
    private final AuthKeyMapper authKeyMapper;

    private final EmailService emailService;

    // 로그인 성공, 실패 시 응답 형식이 달라서 <> 사용하지 않음
    public ResponseEntity signIn(AuthVo requestAuthVo) {
        // 필수 인자가 입력 되지 않았을 경우
        if (requestAuthVo.getUserId() == null || requestAuthVo.getUserId() == null) {
            throw new ValueNullException("필수 인자가 없습니다.");
        }

        String userId = requestAuthVo.getUserId();
        AuthVo selectAuthVo = authMapper.selectByUserId(userId);

        // 로그인 실패 - 아이디가 없는 경우
        if (selectAuthVo == null) {
            ErrorDto errorDto = new ErrorDto().builder().error("존재하지 않는 아이디입니다.").build();
            return ResponseEntity.ok().body(errorDto);
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Boolean signInStatus = passwordEncoder.matches(requestAuthVo.getPassword(), selectAuthVo.getPassword());
        // 로그인 성공
        if (signInStatus) {
            String jwt = JwtManager.generateToken(userId);
            JwtVo jwtVo = new JwtVo().builder().jwt(jwt).build();
            return ResponseEntity.ok().body(jwtVo);
        } else {
            // 로그인 실패 - 비밀번호가 틀린 경우
            ErrorDto errorDto = new ErrorDto().builder().error("비밀번호가 일치하지 않습니다.").build();
            return ResponseEntity.ok().body(errorDto);
        }
    }

    // 회원 가입
    public void signUp(UserAuthVo userAuthVo) {
        // 필수 인자
        String userId = userAuthVo.getUserId();
        String password = userAuthVo.getPassword();
        String name = userAuthVo.getName();
        String email = userAuthVo.getEmail();

        // 선택 인자
        String company = userAuthVo.getCompany();

        // 필수 인자가 입력 되지 않았을 경우 에러 반환
        if (userId == null || password == null
                || name == null || email == null) {
            throw new ValueNullException("필수 인자가 없습니다.");
        }

        UserVo existUser;

        // ID 존재 여부 확인
        existUser = userMapper.selectByUserId(userId);
        if (existUser != null) {
            throw new ExistsException("이미 존재하는 ID 입니다.");
        }

        // Email 존재 여부 확인
        existUser = userMapper.selectByEmail(email);
        if (existUser != null) {
            throw new ExistsException("이미 존재하는 Email 입니다.");
        }

        // 비밀번호 암호화
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        userAuthVo.setPassword(hashedPassword);

        // UserAuthVo -> UserVo
        UserVo userVo = new UserVo().builder().userId(userId)
                .email(email).company(company)
                .name(name).build();

        // UserAuthVo -> AuthVo
        AuthVo authVo = new AuthVo().builder().userId(userAuthVo.getUserId())
                .password(userAuthVo.getPassword()).build();

        // TODO try 로 에러 처리? or ExceptionHandler 에 등록?
        // DB 에 저장
        userMapper.insert(userVo);
        authMapper.insert(authVo);
    }


    // 인증번호 전송
    public void sendAuthKey(UserVo userVo) {
        String email = userVo.getEmail();
        String userId = userVo.getUserId();

        // 필수 인자가 입력 되지 않았을 경우 에러 반환
        if (userId == null || email == null) {
            throw new ValueNullException("필수 인자가 없습니다.");
        }

        // 인증 번호 생성
        int length = 6;
        boolean useLetters = true;
        boolean useNumbers = true;
        String authKey = RandomStringUtils.random(length, useLetters, useNumbers);

        // 인증 번호 저장
        AuthKeyVo authKeyVo = new AuthKeyVo().builder().userId(userId).authKey(authKey)
                .email(email).crateTime(LocalDateTime.now()).build();
        authKeyMapper.insert(authKeyVo);

        // 이메일 전송
        emailService.sendAuthKey(email, authKey);
    }

    // 아이디 중복 확인
    public ResponseEntity<CheckVo> checkUserId(String userId) {
        // 필수 인자가 입력 되지 않았을 경우 에러 반환
        if (userId == null) {
            throw new ValueNullException("필수 인자가 없습니다.");
        }

        UserVo existUser = userMapper.selectByUserId(userId);

        // 아이디로 조회된 사용자가 없을 경우 true / 있을 경우 false
        if (existUser != null) {
            CheckVo checkVo = new CheckVo().builder().check(true).build();
            return ResponseEntity.ok().body(checkVo);
        }
        CheckVo checkVo = new CheckVo().builder().check(false).build();
        return ResponseEntity.ok().body(checkVo);
    }

    // 이메일 중복 확인
    public ResponseEntity<CheckVo> checkEmail(String email) {
        // 필수 인자가 입력 되지 않았을 경우 에러 반환
        if (email == null) {
            throw new ValueNullException("필수 인자가 없습니다.");
        }

        UserVo existUser = userMapper.selectByEmail(email);

        // 이메일로 조회된 사용자가 없을 경우 true / 있을 경우 false
        if (existUser != null) {
            CheckVo checkVo = new CheckVo().builder().check(true).build();
            return ResponseEntity.ok().body(checkVo);
        }
        CheckVo checkVo = new CheckVo().builder().check(false).build();
        return ResponseEntity.ok().body(checkVo);
    }

    // 인증키 확인
    public ResponseEntity<CheckVo> checkAuthKey(AuthKeyVo authKeyVo) {
        String userId = authKeyVo.getUserId();
        String authKey = authKeyVo.getAuthKey();

        // 필수 인자가 입력 되지 않았을 경우 에러 반환
        if (userId == null || authKey == null) {
            throw new ValueNullException("필수 인자가 없습니다.");
        }

        AuthKeyVo readAuthKeyVo = authKeyMapper.selectByUserId(userId);
        // 인증키가 없을 경우
        if (Objects.isNull(readAuthKeyVo)) {
            throw new ValueNullException("조회된 인증번호가 없습니다.");
        }


        String readAuthKey = readAuthKeyVo.getAuthKey();
        LocalDateTime readCreateTime = readAuthKeyVo.getCrateTime();

        CheckVo checkVo;
        // 전송 된 지 10분 지났으면 유효하지 않은 키
        if (readCreateTime.isAfter(LocalDateTime.now().minusMinutes(10)) && authKey.equals(readAuthKey)) {
            checkVo = new CheckVo().builder().check(true).build();
        } else {
            checkVo = new CheckVo().builder().check(false).build();
        }
        return ResponseEntity.ok().body(checkVo);
    }

    // 개인 정보 수정을 위한 비밀번호 확인
    public ResponseEntity<CheckVo> checkPassword(String userIdInJwt, AuthVo authVo) {
        String password = authVo.getPassword();

        // 필수 인자가 입력 되지 않았을 경우 에러 반환
        if (password == null) {
            throw new ValueNullException("필수 인자가 없습니다.");
        }

        AuthVo existAuthVo = authMapper.selectByUserId(userIdInJwt);

        // 비밀번호 확인
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Boolean isSame = passwordEncoder.matches(authVo.getPassword(), existAuthVo.getPassword());

        CheckVo checkVo;
        if (isSame) {
            checkVo = new CheckVo().builder().check(true).build();
            return ResponseEntity.ok().body(checkVo);
        } else {
            checkVo = new CheckVo().builder().check(false).build();
            return ResponseEntity.ok().body(checkVo);
        }
    }

    // 비밀번호 변경
    public void changePassword(String userIdInJwt, AuthVo authVo) {
        String password = authVo.getPassword();
        String userId = authVo.getUserId();

        // 필수 인자가 입력 되지 않았을 경우 에러 반환
        if (password == null) {
            throw new ValueNullException("필수 인자가 없습니다.");
        }

        // 권한이 없는 사용자 정보를 수정하려 할 경우
        if (!userId.equals(userIdInJwt)) {
            throw new DifferentAuthException("접근 할 수 있는 권한이 없습니다.");
        }

        AuthVo existAuthVo = authMapper.selectByUserId(userIdInJwt);

        // 비밀번호 변경
        // 비밀번호 암호화
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        existAuthVo.setPassword(hashedPassword);

        authMapper.update(existAuthVo);
    }
}
