package xyz.parkh.doing.mapper;

import xyz.parkh.doing.domain.UserVo;

public interface UserMapper {
    UserVo selectUserByUserId(String userId);
}
