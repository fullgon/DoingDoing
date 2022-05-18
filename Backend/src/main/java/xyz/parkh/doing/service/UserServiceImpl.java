package xyz.parkh.doing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.parkh.doing.domain.User;
import xyz.parkh.doing.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    public User getUserByUserId(String userId) {
        User user = userMapper.selectUserByUserId(userId);
        return user;
    }
}
