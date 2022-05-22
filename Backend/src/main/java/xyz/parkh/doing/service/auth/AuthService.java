package xyz.parkh.doing.service.auth;

import xyz.parkh.doing.domain.AuthVo;

public interface AuthService {
    AuthVo read(String userId);

}
