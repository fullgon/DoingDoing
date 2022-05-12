package xyz.parkh.campus.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.parkh.campus.entity.User;
import xyz.parkh.campus.repository.UserRepository;


@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(final User userEntity) {
        if(userEntity == null || userEntity.getUserId() == null) {
            throw new RuntimeException("Invalid arguments");
        }
        final String userId = userEntity.getUserId();
        if(userRepository.existsByUserId(userId)) {
            log.warn("Id already exists {}", userId);
            throw new RuntimeException("Id already exists");
        }
        return userRepository.save(userEntity);
    }
    public User getByCredentials(final String id, final String password, final PasswordEncoder encoder) {
        final User originalUser = userRepository.findByUserId(id);

        if (originalUser != null && encoder.matches(password, originalUser.getPassword())) {
            return originalUser;
        }
        return null;
    }

    public User updateUser(final User userEntity) {
        User user = userRepository.save(userEntity);
        return user;
    }
}
