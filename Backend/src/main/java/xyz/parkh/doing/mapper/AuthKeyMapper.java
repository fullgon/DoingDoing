package xyz.parkh.doing.mapper;

import xyz.parkh.doing.domain.AuthKeyVo;

import java.util.List;

public interface AuthKeyMapper {

    int insert(AuthKeyVo authKeyVo);

    AuthKeyVo selectByNo(Integer no);

    List<AuthKeyVo> selectAll();

    int update(AuthKeyVo authKeyVo);

    int delete(Integer no);
}
