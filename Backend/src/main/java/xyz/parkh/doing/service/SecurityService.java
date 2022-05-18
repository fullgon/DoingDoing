package xyz.parkh.doing.security;

import org.springframework.stereotype.Service;

@Service
public interface SecurityService {
    String createToken(String subject, long ttMills);

    String getSubject(String token);
}
