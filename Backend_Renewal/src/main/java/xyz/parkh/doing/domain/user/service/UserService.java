package xyz.parkh.doing.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public UserSimpleInfo signUp(final UserDetailInfo userDetailInfo) {
        User user = userDetailInfo.convertToUser();
        userRepository.save(user);

        User findUser = userRepository.findById(user.getId()).get();
        UserSimpleInfo findUserSimpleInfo = findUser.convertToUserSimpleInfo();
        return findUserSimpleInfo;
    }

    public Boolean signIn(final Auth auth) {
        User findUser = userRepository.findByAuthId(auth.getAuthId());
        String savedPassword = findUser.getPassword();
        String requestPassword = auth.getPassword();

        return savedPassword.equals(requestPassword);
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

}
