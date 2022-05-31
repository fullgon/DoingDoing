package xyz.parkh.doing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.entity.UserVo;
import xyz.parkh.doing.exception.DifferentAuthException;
import xyz.parkh.doing.mapper.UserMapper;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public ResponseEntity<UserVo> findUser(final String userIdInJwt, final String userId) {
        // TODO 친구 기능 추가 시 권한 확인하는 코드 작성 필요
        // 현재는 본인 정보만 조회 가능.
        if (!userId.equals(userIdInJwt)) {
            throw new DifferentAuthException("접근 할 수 있는 권한이 없습니다.");
        }
        UserVo userVo = userMapper.selectByUserId(userId);

        return ResponseEntity.ok().body(userVo);
    }

    public ResponseEntity modifyUser(final String userIdInJwt, final UserVo userVo) {
        // 본인 정보만 수정 가능
        userVo.setUserId(userIdInJwt);
        userMapper.update(userVo);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity removeUser(final String userIdInJwt) {
        // 본인 정보만 삭제 가능
        userMapper.delete(userIdInJwt);

        return ResponseEntity.noContent().build();
    }
}
