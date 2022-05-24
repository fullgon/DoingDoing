package xyz.parkh.doing.service.user;

import xyz.parkh.doing.domain.UserVo;

import java.util.Map;

public interface UserService {

    UserVo read(String userId);

    void create(UserVo userVo);

    Map<String, Object> getByUserId(String userId);

    Map<String, Object> patchByUserId(String userId, UserVo userVo);

    Map<String, Object> deleteByUserId(String userId);
}

