package xyz.parkh.doing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.entity.AuthKeyVo;
import xyz.parkh.doing.domain.entity.AuthVo;
import xyz.parkh.doing.domain.entity.UserVo;
import xyz.parkh.doing.domain.model.CheckDto;
import xyz.parkh.doing.domain.model.ErrorDto;
import xyz.parkh.doing.domain.model.JwtDto;
import xyz.parkh.doing.domain.model.UserAuthDto;
import xyz.parkh.doing.exception.DifferentAuthException;
import xyz.parkh.doing.exception.ValueException;
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
            throw new ValueException("필수 인자가 없습니다.");
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
            JwtDto jwtDto = new JwtDto().builder().jwt(jwt).build();
            return ResponseEntity.ok().body(jwtDto);
        } else {
            // 로그인 실패 - 비밀번호가 틀린 경우
            ErrorDto errorDto = new ErrorDto().builder().error("비밀번호가 일치하지 않습니다.").build();
            return ResponseEntity.ok().body(errorDto);
        }
    }

    // 회원 가입
    public ResponseEntity signUp(UserAuthDto userAuthDto) {
        // 필수 인자
        String userId = userAuthDto.getUserId();
        String password = userAuthDto.getPassword();
        String name = userAuthDto.getName();
        String email = userAuthDto.getEmail();

        // 선택 인자
        String company = userAuthDto.getCompany();

        // 필수 인자가 입력 되지 않았을 경우 에러 반환
        if (userId == null || password == null
                || name == null || email == null) {
            throw new ValueException("필수 인자가 없습니다.");
        }

        UserVo existUser;

        // ID 존재 여부 확인
        existUser = userMapper.selectByUserId(userId);
        if (existUser != null) {
            throw new ValueException("이미 존재하는 ID 입니다.");
        }

        // Email 존재 여부 확인
        existUser = userMapper.selectByEmail(email);
        if (existUser != null) {
            throw new ValueException("이미 존재하는 Email 입니다.");
        }

        // 비밀번호 암호화
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        userAuthDto.setPassword(hashedPassword);

        // UserAuthVo -> UserVo
        UserVo userVo = new UserVo().builder().userId(userId)
                .email(email).company(company)
                .name(name).build();

        // UserAuthVo -> AuthVo
        AuthVo authVo = new AuthVo().builder().userId(userAuthDto.getUserId())
                .password(userAuthDto.getPassword()).build();

        // TODO try 로 에러 처리? or ExceptionHandler 에 등록?
        // DB 에 저장
        userMapper.insert(userVo);
        authMapper.insert(authVo);

        return ResponseEntity.noContent().build();
    }


    // 인증번호 전송
    public ResponseEntity sendAuthKey(final UserVo userVo) {
        String email = userVo.getEmail();
        String userId = userVo.getUserId();

        // 필수 인자가 입력 되지 않았을 경우 에러 반환
        if (userId == null || email == null) {
            throw new ValueException("필수 인자가 없습니다.");
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

        return ResponseEntity.noContent().build();
    }

    // 아이디 중복 확인
    public ResponseEntity<CheckDto> checkUserId(final String userId) {
        // 필수 인자가 입력 되지 않았을 경우 에러 반환
        if (userId == null) {
            throw new ValueException("필수 인자가 없습니다.");
        }

        UserVo existUser = userMapper.selectByUserId(userId);

        // 아이디로 조회된 사용자가 없을 경우 true / 있을 경우 false
        if (existUser != null) {
            CheckDto checkDto = new CheckDto().builder().check(true).build();
            return ResponseEntity.ok().body(checkDto);
        }
        CheckDto checkDto = new CheckDto().builder().check(false).build();
        return ResponseEntity.ok().body(checkDto);
    }

    // 이메일 중복 확인
    public ResponseEntity<CheckDto> checkEmail(final String email) {
        // 필수 인자가 입력 되지 않았을 경우 에러 반환
        if (email == null) {
            throw new ValueException("필수 인자가 없습니다.");
        }

        UserVo existUser = userMapper.selectByEmail(email);

        // 이메일로 조회된 사용자가 없을 경우 true / 있을 경우 false
        if (existUser != null) {
            CheckDto checkDto = new CheckDto().builder().check(true).build();
            return ResponseEntity.ok().body(checkDto);
        }
        CheckDto checkDto = new CheckDto().builder().check(false).build();
        return ResponseEntity.ok().body(checkDto);
    }

    // 인증키 확인
    public ResponseEntity<CheckDto> checkAuthKey(final AuthKeyVo authKeyVo) {
        String userId = authKeyVo.getUserId();
        String authKey = authKeyVo.getAuthKey();

        // 필수 인자가 입력 되지 않았을 경우 에러 반환
        if (userId == null || authKey == null) {
            throw new ValueException("필수 인자가 없습니다.");
        }

        AuthKeyVo readAuthKeyVo = authKeyMapper.selectByUserId(userId);
        // 인증키가 없을 경우
        if (Objects.isNull(readAuthKeyVo)) {
            throw new ValueException("조회된 인증번호가 없습니다.");
        }

        String readAuthKey = readAuthKeyVo.getAuthKey();
        LocalDateTime readCreateTime = readAuthKeyVo.getCrateTime();

        CheckDto checkDto;
        // 전송 된 지 10분 지났으면 유효하지 않은 키
        if (readCreateTime.isAfter(LocalDateTime.now().minusMinutes(10)) && authKey.equals(readAuthKey)) {
            checkDto = new CheckDto().builder().check(true).build();
        } else {
            checkDto = new CheckDto().builder().check(false).build();
        }
        return ResponseEntity.ok().body(checkDto);
    }

    // 개인 정보 수정을 위한 비밀번호 확인
    public ResponseEntity<CheckDto> checkPassword(final String userIdInJwt, final AuthVo authVo) {
        String password = authVo.getPassword();

        // 필수 인자가 입력 되지 않았을 경우 에러 반환
        if (password == null) {
            throw new ValueException("필수 인자가 없습니다.");
        }

        AuthVo existAuthVo = authMapper.selectByUserId(userIdInJwt);

        // 비밀번호 확인
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Boolean isSame = passwordEncoder.matches(authVo.getPassword(), existAuthVo.getPassword());

        CheckDto checkDto = new CheckDto().builder().check(isSame).build();
        return ResponseEntity.ok().body(checkDto);
    }

    // 비밀번호 변경
    public ResponseEntity modifyPassword(final String userIdInJwt, final AuthVo authVo) {
        String password = authVo.getPassword();
        String userId = authVo.getUserId();

        // 필수 인자가 입력 되지 않았을 경우 에러 반환
        if (password == null) {
            throw new ValueException("필수 인자가 없습니다.");
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
        return ResponseEntity.noContent().build();
    }
}
