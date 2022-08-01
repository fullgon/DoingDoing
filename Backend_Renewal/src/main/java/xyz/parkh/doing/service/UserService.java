package xyz.parkh.doing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.entity.user.User;
import xyz.parkh.doing.domain.model.Auth;
import xyz.parkh.doing.domain.model.IndividualDetailInfo;
import xyz.parkh.doing.domain.model.UserInfo;
import xyz.parkh.doing.repository.UserRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserInfo findUserInfo(final String userId) {
        User findUser = userRepository.findByUserId(userId);
        UserInfo findUserInfo = findUser.convertToUserInfo();

        return findUserInfo;
    }

    public UserInfo signUp(final IndividualDetailInfo individualDetailInfo) {
        User user = individualDetailInfo.convertToUserEntity();
        userRepository.save(user);

        User findUser = userRepository.findByNo(user.getNo());
        UserInfo findUserInfo = findUser.convertToUserInfo();
        return findUserInfo;
    }

    public Boolean signIn(Auth auth) {
        User findUser = userRepository.findByUserId(auth.getUserId());
        String savedPassword = findUser.getPassword();
        String requestPassword = auth.getPassword();

        return savedPassword.equals(requestPassword);
    }

    public IndividualDetailInfo modifyUserInfo(final UserInfo modifyUserInfo) {
        String userId = modifyUserInfo.getUserId();
        User findUser = userRepository.findByUserId(userId);

        findUser.modifyUserInfo(modifyUserInfo);
        IndividualDetailInfo findIndividualDetailInfo = findUser.convertToIndividualUser();
        return findIndividualDetailInfo;
    }

    public void remove(final String userId) {
        User findUser = userRepository.findByUserId(userId);
        userRepository.delete(findUser);
    }

    public Boolean isExistUserByUserId(String userId) {
        User existUser = userRepository.findByUserId(userId);
        return existUser != null;
    }

    // 회원 가입이 가능한 아이디인지 확인
    public Boolean isAbleSignUpUserId(String userId) {
        return !isExistUserByUserId(userId);
    }

    public Boolean isExistUserByEmail(String email) {
        User existUser = userRepository.findByEmail(email);
        return existUser != null;
    }

    // 회원 가임이 가능한 이메일인지 확인
    public Boolean isAbleSignUpEmail(String email) {
        return !isExistUserByEmail(email);
    }

    public void modifyPassword(Auth auth) {
        User findUser = userRepository.findByUserId(auth.getUserId());
        System.out.println("findUserEntity = " + findUser.getPassword());
        findUser.modifyPassword(auth);
        System.out.println("findUserEntity = " + findUser.getPassword());
    }
}
