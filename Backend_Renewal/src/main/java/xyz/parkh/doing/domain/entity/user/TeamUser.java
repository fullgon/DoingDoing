package xyz.parkh.doing.domain.entity.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamUser extends User {
    @Builder
    public TeamUser(String userId, String password, String name, String email, String company) {
        setUserId(userId);
        setPassword(password);
        setName(name);
        setEmail(email);
        setCompany(company);
    }
}
