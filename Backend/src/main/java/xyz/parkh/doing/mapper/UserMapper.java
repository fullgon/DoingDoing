package xyz.parkh.doing.mapper;

import xyz.parkh.doing.domain.UserVo;

public interface UserMapper {
    UserVo selectUserByUserId(String userId);

    int updateUser(UserVo userVo);

    int deleteUser(String userId);
}
