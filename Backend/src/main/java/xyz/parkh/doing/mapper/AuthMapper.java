package xyz.parkh.doing.mapper;

import xyz.parkh.doing.domain.AuthVo;

public interface AuthMapper {

    int insertAuth(AuthVo authVo);

    AuthVo selectAuthByUserId(String UserId);

    int updateAuth(AuthVo authVo);

    int deleteAuthByUserId(String UserId);
}
