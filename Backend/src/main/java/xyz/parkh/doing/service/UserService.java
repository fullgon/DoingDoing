package xyz.parkh.doing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.UserVo;
import xyz.parkh.doing.mapper.UserMapper;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public Map<String, Object> readByUserId(String userIdInJwt, String userId) {
        UserVo userVo = userMapper.selectByUserId(userId);

        HashMap<String, Object> jsonData = new HashMap<>();
        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "ok");
        result.put("message", userVo);
        jsonData.put("result", result);
        return jsonData;
    }

    public UserVo readByEmail(String email) {
        UserVo userVo = userMapper.selectByEmail(email);
        return userVo;
    }

    public void create(UserVo userVo) {
        userMapper.insert(userVo);
    }

    public Map<String, Object> modifyUser(String userIdInJwt, UserVo userVo) {
        userMapper.update(userVo);
        HashMap<String, Object> jsonData = new HashMap<>();
        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "ok");
        result.put("message", "수정되었습니다.");
        jsonData.put("result", result);
        return jsonData;
    }

    public Map<String, Object> removeUser(String userIdInJwt) {
        HashMap<String, Object> jsonData = new HashMap<>();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", "message");
        jsonData.put("result", result);
        return jsonData;
    }

    public Map<String, Object> getUser(String userIdInJwt, UserVo userVo) {
        HashMap<String, Object> jsonData = new HashMap<>();
        UserVo user = userMapper.selectByUserId(userIdInJwt);
        jsonData.put("user", user);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", "message");
        jsonData.put("result", result);
        return jsonData;
    }
}
