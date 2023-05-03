package com.ecore.roles.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Membership {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    @Type(type="uuid-char")
    @Column(name = "role_id", nullable = false)
    private UUID roleId;

    @Type(type="uuid-char")
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Type(type="uuid-char")
    @Column(name = "team_id", nullable = false)
    private UUID teamId;

    public Membership(UUID id, UUID roleId, UUID userId, UUID teamId){
        this.setId(id);
        this.setRoleId(roleId);
        this.setUserId(userId);
        this.setTeamId(teamId);
    }

}
