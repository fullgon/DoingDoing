package xyz.parkh.doing.domain.entity.user;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IndividualUser extends User {

    @Builder
    public IndividualUser(String userId, String password, String name, String email, String company) {
        setUserId(userId);
        setPassword(password);
        setName(name);
        setEmail(email);
        setCompany(company);
    }
}
