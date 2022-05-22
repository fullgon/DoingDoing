package xyz.parkh.doing.service.auth;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.parkh.doing.domain.AuthVo;
import xyz.parkh.doing.mapper.AuthMapper;

@Log4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthMapper authMapper;

    public AuthVo read(String userId){
        AuthVo authVo = authMapper.selectByUserId(userId);
        return authVo;
    }

}
