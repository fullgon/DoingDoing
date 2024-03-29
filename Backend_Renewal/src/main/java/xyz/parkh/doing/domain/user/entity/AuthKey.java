package xyz.parkh.doing.domain.user.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.parkh.doing.domain.user.model.AuthKeyType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "AUTHKEY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthKey {

    @Id
    @GeneratedValue
    @Column(name = "AUTHKEY_ID")
    private Long id;

    @Column(length = 20)
    private String authId;

    @Column(length = 50)
    private String email;

    @Column(length = 50)
    private String authKey;

    private LocalDateTime createDateTime;

    @Enumerated(value = EnumType.STRING)
    private AuthKeyType authKeyType;
}
