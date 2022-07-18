package xyz.parkh.doing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.entity.UserEntity;
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

}
