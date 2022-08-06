package xyz.parkh.doing.domain.entity.user;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    private String teamAuthId;

    private String teamName;

    @OneToMany(mappedBy = "id")
    private List<TeamUser> members = new ArrayList<>();


}
