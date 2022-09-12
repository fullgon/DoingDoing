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

    public User findByAuthId(final String authId) {
        User findByAuthId = userRepository.findByAuthId(authId);
        return findByAuthId;
    }

    public UserSimpleInfo findUser(final String userId) {
        User findUser = userRepository.findByAuthId(userId);
        UserSimpleInfo findUserSimpleInfo = findUser.convertToUserSimpleInfo();

        return findUserSimpleInfo;
    }

    //
    public User signUp(final UserDetailInfo userDetailInfo) {
        User user = userDetailInfo.convertToUser();
        User findUser = findByAuthId(user.getAuthId());
        if (findUser != null) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }

        userRepository.save(user);
        return user;
    }

    public Boolean signIn(final Auth auth) {
        User signInUser = getByCredentials(auth.getAuthId(), auth.getPassword());
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

    public User getByCredentials(final String authId, final String password) {
        final User oriUser = findByAuthId(authId);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (oriUser != null && encoder.matches(password, oriUser.getPassword())) {
            return oriUser;
        }
        return null;
    }

}
