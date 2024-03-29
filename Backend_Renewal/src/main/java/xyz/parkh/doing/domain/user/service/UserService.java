package xyz.parkh.doing.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.user.model.Auth;
import xyz.parkh.doing.domain.user.model.UserDetailInfo;
import xyz.parkh.doing.domain.user.model.UserSimpleInfo;
import xyz.parkh.doing.domain.user.repository.UserRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User findByAuthId(final String authId) {
        User findByAuthId = userRepository.findByAuthId(authId);
        return findByAuthId;
    }

    public UserSimpleInfo findUser(final String userId) {
        User findUser = userRepository.findByAuthId(userId);

        UserSimpleInfo findUserSimpleInfo = new UserSimpleInfo(findUser.getAuthId(), findUser.getName(),
                findUser.getEmail(), findUser.getCompany());

        return findUserSimpleInfo;
    }

    // 회원 가입
    public User signUp(final UserDetailInfo userDetailInfo) {
        String authId = userDetailInfo.getAuthId();
        String password = encodePassword(userDetailInfo.getPassword()); // 암호화
        String name = userDetailInfo.getName();
        String email = userDetailInfo.getEmail();
        String company = userDetailInfo.getCompany();

        User user = new User(authId, password, name, email, company);

        User findUser = findByAuthId(authId);
        if (findUser != null) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }

        userRepository.save(user);
        return user;
    }

    // 로그인
    public Boolean signIn(final Auth auth) {
        User signInUser = getByCredentials(auth);
        if (signInUser == null) {
            return false;
        }
        return true;
    }

    public void modifyUser(final UserSimpleInfo modifyUser) {
        String userId = modifyUser.getAuthId();
        User findUser = userRepository.findByAuthId(userId);
        findUser.modifyUser(modifyUser);
    }

    public void modifyPassword(final Auth auth) {
        User findUser = userRepository.findByAuthId(auth.getAuthId());
        findUser.modifyPassword(auth);
    }

    public void remove(final String userId) {
        User findUser = userRepository.findByAuthId(userId);
        userRepository.delete(findUser);
    }

    public Boolean isExistUserByUserId(final String userId) {
        User existUser = userRepository.findByAuthId(userId);
        return existUser != null;
    }

    public Boolean isAbleSignUpUserId(final String userId) {
        return !isExistUserByUserId(userId);
    }

    public Boolean isExistUserByEmail(final String email) {
        User existUser = userRepository.findByEmail(email);
        return existUser != null;
    }

    public Boolean isAbleSignUpEmail(final String email) {
        return !isExistUserByEmail(email);
    }

    public User getByCredentials(final Auth auth) {
        final String authId = auth.getAuthId();
        final String password = auth.getPassword();
        final User oriUser = findByAuthId(authId);

        if (oriUser != null && encoder.matches(password, oriUser.getPassword())) {
            return oriUser;
        }
        return null;
    }

    public String encodePassword(final String password) {
        return encoder.encode(password);
    }

}
