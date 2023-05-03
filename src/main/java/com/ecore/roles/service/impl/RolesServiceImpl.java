package com.ecore.roles.service.impl;

import com.ecore.roles.exception.*;
import com.ecore.roles.model.*;
import com.ecore.roles.repository.*;
import com.ecore.roles.service.MembershipsService;
import com.ecore.roles.service.RolesService;
import com.ecore.roles.web.dto.*;
import com.ecore.roles.web.rest.*;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Log4j2
@Service
public class RolesServiceImpl implements RolesService {

    public static final String DEFAULT_ROLE = "Developer";

    private Logger logger = Logger.getLogger(RolesServiceImpl.class.getName());

    private final RoleRepository repository;
    private final MembershipRepository membershipsRepository;

    @Autowired
    public RolesServiceImpl(
            RoleRepository roleRepository,
            MembershipRepository membershipsRepository) {
        this.repository = roleRepository;
        this.membershipsRepository = membershipsRepository;
    }

    @Override
    public List<MembershipDto> findByUserIdAndTeamId(UUID userId, UUID teamId){
        var result = membershipsRepository.findByUserIdAndTeamId(userId, teamId);
        List<MembershipDto> listResult = new ArrayList<>();
        for (Membership m: result) {
            listResult.add(MembershipDto.fromModel(m));
        }
        return listResult;
    }

    @Override
    public List<RoleDto> findAll() {
        List<Role> roles = repository.findAll();
        List<RoleDto> roleDtos = new ArrayList<>();
        roles.forEach(t -> roleDtos.add(RoleDto.fromModel(t)));
        roleDtos.stream().forEach(t -> t.add(linkTo(methodOn(RolesRestController.class).findById(t.getId())).withSelfRel()));
        return roleDtos;
    }

    @Override
    public RoleDto findById(UUID roleId) {
        logger.info("Finding role by id " + roleId);
        Role role = repository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class, roleId));
        RoleDto roleDto = RoleDto.fromModel(role);
        roleDto.add(linkTo(methodOn(RolesRestController.class).findById(roleId)).withSelfRel());
        return roleDto;
    }

    @Override
    public RoleDto create(RoleDto roleDto) {
        logger.info("Creating role " + roleDto.getName());
        var dto = RoleDto.fromModel(repository.save(roleDto.toModel()));
        dto.add(linkTo(methodOn(RolesRestController.class).findById(dto.getId())).withSelfRel());
        return dto;
    }

    @Override
    public RoleDto update(RoleDto roleDto) {
        if(roleDto == null) throw new RequiredObjectsNullException();
        logger.info("Updating a Role!");
        Role role = repository.findById(roleDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(Role.class, roleDto.getId()));
        role.setName(roleDto.getName());
        role.setId(roleDto.getId());
        var dto = RoleDto.fromModel(repository.save(role));
        dto.add(linkTo(methodOn(RolesRestController.class).findById(roleDto.getId())).withSelfRel());
        return dto;
    }

    @Override
    public void delete(UUID roleId) {
        logger.info("Deleting role with id " + roleId);
        Role role = repository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class, roleId));
        repository.delete(role);
    }

}
