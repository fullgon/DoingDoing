package xyz.parkh.doing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
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
