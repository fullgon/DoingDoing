package xyz.parkh.doing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import xyz.parkh.doing.domain.entity.UserEntity;
import xyz.parkh.doing.domain.model.Auth;
import xyz.parkh.doing.domain.model.User;
import xyz.parkh.doing.domain.model.UserInfo;
import xyz.parkh.doing.repository.UserRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserInfo findUserInfo(final String userId) {
        UserEntity findUserEntity = userRepository.findByUserId(userId);
        UserInfo findUserInfo = findUserEntity.convertToUserInfo();

        return findUserInfo;
    }

    public UserInfo signUp(final User user) {
        UserEntity userEntity = user.convertToUserEntity();
        userRepository.save(userEntity);

        UserEntity findUserEntity = userRepository.findByNo(userEntity.getNo());
        UserInfo findUserInfo = findUserEntity.convertToUserInfo();
        return findUserInfo;
    }

    public Boolean signIn(Auth auth) {
        UserEntity findUserEntity = userRepository.findByUserId(auth.getUserId());
        String savedPassword = findUserEntity.getPassword();
        String requestPassword = auth.getPassword();

        return savedPassword.equals(requestPassword);
    }

    public User modifyUserInfo(final UserInfo modifyUserInfo) {
        String userId = modifyUserInfo.getUserId();
        UserEntity findUserEntity = userRepository.findByUserId(userId);

        findUserEntity.modifyUserInfo(modifyUserInfo);
        User findUser = findUserEntity.convertToUser();
        return findUser;
    }

    public void remove(final String userId) {
        UserEntity findUserEntity = userRepository.findByUserId(userId);
        userRepository.delete(findUserEntity);
    }

    public Boolean isExistUserByUserId(String userId) {
        UserEntity existUserEntity = userRepository.findByUserId(userId);
        return existUserEntity != null;
    }

    // 회원 가입이 가능한 아이디인지 확인
    public Boolean isAbleSignUpUserId(String userId) {
        return !isExistUserByUserId(userId);
    }

    public Boolean isExistUserByEmail(String email) {
        UserEntity existUserEntity = userRepository.findByEmail(email);
        return existUserEntity != null;
    }

    // 회원 가임이 가능한 이메일인지 확인
    public Boolean isAbleSignUpEmail(String email) {
        return !isExistUserByEmail(email);
    }

    public void modifyPassword(Auth auth) {
        UserEntity findUserEntity = userRepository.findByUserId(auth.getUserId());
        System.out.println("findUserEntity = " + findUserEntity.getPassword());
        findUserEntity.modifyPassword(auth);
        System.out.println("findUserEntity = " + findUserEntity.getPassword());
    }
}
