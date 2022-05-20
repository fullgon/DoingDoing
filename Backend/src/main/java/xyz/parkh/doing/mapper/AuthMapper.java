package xyz.parkh.doing.mapper;

import xyz.parkh.doing.domain.AuthVo;

import java.util.List;

public interface AuthMapper {
    int insert(AuthVo authVo);

    AuthVo selectByUserId(String userId);

    List<AuthVo> selectAll();

    int update(AuthVo authVo);

    int delete(String authId);
}
