package xyz.parkh.doing.mapper;

import xyz.parkh.doing.domain.AuthKeyVo;

public interface AuthKeyMapper {

    int insert(AuthKeyVo authKeyVo);

    // UserId 의 가장 최근에 생성된 AuthKeyVo 조회
    AuthKeyVo selectByUserId(String userId);

}
