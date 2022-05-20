package xyz.parkh.doing.mapper;

import xyz.parkh.doing.domain.UserVo;

import java.util.List;

public interface UserMapper {
    int insert(UserVo userVo);

    UserVo selectByUserId(String userId);

    List<UserVo> selectAll();

    int update(UserVo userVo);

    int delete(String userId);
}
