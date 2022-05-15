package xyz.parkh.doing.mapper;

import xyz.parkh.doing.domain.User;

public interface UserMapper {
    User selectUserByUserId(String userId);
}
