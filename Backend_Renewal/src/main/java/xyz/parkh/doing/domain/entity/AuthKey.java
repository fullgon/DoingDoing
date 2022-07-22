package xyz.parkh.doing.domain.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "AUTHKEY")
public class AuthKey {

    @Id
    @GeneratedValue
    @Column(name = "authkey_no")
    private Long no;

    @Column(length = 20)
    private String userId;

    @Column(length = 50)
    private String email;

    @Column(length = 50)
    private String authKey;

    private LocalDateTime createDateTime;

    private Integer type;
}
