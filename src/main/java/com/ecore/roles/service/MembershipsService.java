package com.ecore.roles.service;

import com.ecore.roles.exception.ResourceNotFoundException;
import com.ecore.roles.model.Membership;
import com.ecore.roles.web.dto.*;

import java.util.List;
import java.util.UUID;

public interface MembershipsService {

//    Membership assignRoleToMembership(Membership membership) throws ResourceNotFoundException;
//
//    List<Membership> getMemberships(UUID roleId);

    List<MembershipDto> findAll();
    MembershipDto findById(UUID id);
    MembershipDto create(MembershipDto membership);
    MembershipDto update(MembershipDto membership);
    void delete(UUID id);

}
