package com.ecore.roles.web.dto;

import com.ecore.roles.model.*;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@JsonPropertyOrder({"name"})
public class TeamDto extends RepresentationModel<TeamDto> {

    @JsonProperty
    private UUID id;

    @JsonProperty
    private String name;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UUID teamLeadId;

//    @JsonProperty
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    private List<UUID> teamMemberIds;

    public TeamDto(UUID id, String name, UUID teamLeadId){
        this.id = id;
        this.name = name;
        this.teamLeadId = teamLeadId;
    }

    public static TeamDto fromModel(Team team) {
        if (team == null) {
            return null;
        }
        return TeamDto.builder()
                .id(team.getId())
                .name(team.getName())
                .teamLeadId(team.getTeamLeadId())
//                .teamMemberIds(team.getTeamMemberIds())
                .build();
    }

    public Team toModel() {
        return Team.builder()
                .id(this.id)
                .name(this.name)
                .teamLeadId(this.getTeamLeadId())
                .build();
    }
}
