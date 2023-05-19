package com.ecore.roles.service;

import com.ecore.roles.web.dto.*;

import java.util.List;
import java.util.UUID;

public interface MembershipsService {

    List<MembershipDto> findAll();

    MembershipDto findById(UUID id);

    MembershipDto create(MembershipDto membership);

    MembershipDto update(MembershipDto membership);

    void delete(UUID id);

}
