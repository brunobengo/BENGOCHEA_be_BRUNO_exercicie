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
@EqualsAndHashCode
@JsonPropertyOrder({"firstName", "lastName", "displayName", "avatarUrl", "location"})
public class UserDto extends RepresentationModel<UserDto> {

    @JsonProperty("id")
    @Mapping("id")
    private UUID key;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String firstName;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastName;

    @JsonProperty
    private String displayName;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String avatarUrl;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String location;

    public static UserDto fromModel(User user) {
        if (user == null) {
            return null;
        }
        return UserDto.builder()
                .key(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .displayName(user.getDisplayName())
                .avatarUrl(user.getAvatarUrl())
                .location(user.getLocation())
                .build();
    }

    public User toModel() {
        return User.builder()
                .id(this.key)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .displayName(this.displayName)
                .avatarUrl(this.avatarUrl)
                .location(this.location)
                .build();
    }
}
