package xyz.parkh.doing.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.parkh.doing.domain.UserVo;
import xyz.parkh.doing.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    public UserVo getUserByUserId(String userId) {
        UserVo userVO = userMapper.selectUserByUserId(userId);
        return userVO;
    }
}
