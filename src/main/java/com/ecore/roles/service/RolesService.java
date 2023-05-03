package com.ecore.roles.service;

import com.ecore.roles.model.Role;
import com.ecore.roles.web.dto.*;

import java.util.List;
import java.util.UUID;

public interface RolesService {

    List<MembershipDto> findByUserIdAndTeamId(UUID userId, UUID teamId);

    List<RoleDto> findAll();
    RoleDto findById(UUID id);
    RoleDto create(RoleDto role);
    RoleDto update(RoleDto role);
    void delete(UUID id);


}
