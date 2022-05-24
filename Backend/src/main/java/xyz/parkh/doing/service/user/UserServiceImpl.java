package xyz.parkh.doing.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.UserVo;
import xyz.parkh.doing.mapper.UserMapper;

import java.util.HashMap;
import java.util.Map;

@Log4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public UserVo read(String userId) {
        UserVo userVO = userMapper.selectByUserId(userId);
        return userVO;
    }

    @Override
    public void create(UserVo userVo) {
        userMapper.insert(userVo);
    }

    @Override
    public Map<String, Object> getByUserId(String userId) {
        HashMap<String, Object> jsonData = new HashMap<>();
        UserVo user = read(userId);
        jsonData.put("user", user);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", "message");
        jsonData.put("result", result);
        return jsonData;
    }

    @Override
    public Map<String, Object> patchByUserId(String userId, UserVo userVo) {
        userMapper.update(userVo);
        HashMap<String, Object> jsonData = new HashMap<>();
        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "ok");
        result.put("message", "수정되었습니다.");
        jsonData.put("result", result);
        return jsonData;
    }

    @Override
    public Map<String, Object> deleteByUserId(String userId) {

        HashMap<String, Object> jsonData = new HashMap<>();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", "message");
        jsonData.put("result", result);
        return jsonData;
    }
}
