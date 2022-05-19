package xyz.parkh.doing.service.security;

import org.springframework.stereotype.Service;

public interface SecurityService{
    String createToken(String subject, long ttMills);

    String getSubject(String token);
}
