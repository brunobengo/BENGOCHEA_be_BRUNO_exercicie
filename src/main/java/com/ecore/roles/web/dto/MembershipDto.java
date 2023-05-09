package com.ecore.roles.web.dto;

import com.ecore.roles.model.*;
import com.fasterxml.jackson.annotation.*;
import com.github.dozermapper.core.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonPropertyOrder({"roleId", "userId", "teamId"})
public class MembershipDto extends RepresentationModel<MembershipDto> {

    @JsonProperty("id")
    @Mapping("id")
    private UUID key;

    // @JsonProperty
    private Role role;

    private User user;

    // @JsonProperty
    private Team team;

    public static MembershipDto fromModel(Membership membership) {
        if (membership == null) {
            return null;
        }
        return MembershipDto.builder()
                .key(membership.getId())
                .role(membership.getRole())
                .user(membership.getUser())
                .team(membership.getTeam())
                .build();
    }

    public Membership toModel() {
        return Membership.builder()
                .id(this.key)
                .role(this.role)
                .user(this.user)
                .team(this.team)
                .build();
    }

}
