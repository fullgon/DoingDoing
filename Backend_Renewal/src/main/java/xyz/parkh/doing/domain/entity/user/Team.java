package xyz.parkh.doing.domain.entity.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team extends User {
    @Builder
    public Team(String userId, String password, String name, String email, String company) {
        super.setUserId(userId);
        super.setPassword(password);
        super.setName(name);
        super.setEmail(email);
        super.setCompany(company);
    }
}
