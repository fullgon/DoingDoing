package xyz.parkh.doing.service.user;

import xyz.parkh.doing.domain.UserVo;

public interface UserService {

    UserVo read(String userId);

    void create(UserVo userVo);
}

