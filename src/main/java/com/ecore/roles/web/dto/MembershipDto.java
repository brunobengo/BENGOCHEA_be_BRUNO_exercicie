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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

import static java.util.Optional.ofNullable;

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

    @JsonProperty
    private UUID roleId;

    @JsonProperty()
    private UUID userId;

    @JsonProperty
    private UUID teamId;

    public static MembershipDto fromModel(Membership membership) {
        if (membership == null) {
            return null;
        }
        return MembershipDto.builder()
                .key(membership.getId())
                .roleId(membership.getRoleId())
                .userId(membership.getUserId())
                .teamId(membership.getTeamId())
                .build();
    }

    public Membership toModel() {
        return Membership.builder()
                .id(this.key)
                .roleId(this.roleId)
                .userId(this.userId)
                .teamId(this.teamId)
                .build();
    }

}
