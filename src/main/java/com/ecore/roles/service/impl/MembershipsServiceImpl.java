package com.ecore.roles.service.impl;

import com.ecore.roles.exception.*;
import com.ecore.roles.model.*;
import com.ecore.roles.repository.*;
import com.ecore.roles.service.MembershipsService;
import com.ecore.roles.web.dto.*;
import com.ecore.roles.web.rest.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Log4j2
@Service
public class MembershipsServiceImpl implements MembershipsService {

//    private final MembershipRepository repository;
//    private final RoleRepository roleRepository;
//
//    @Autowired
//    public MembershipsServiceImpl(
//            MembershipRepository membershipRepository,
//            RoleRepository roleRepository) {
//        this.membershipRepository = membershipRepository;
//        this.roleRepository = roleRepository;
//    }

    @Autowired
    private MembershipRepository repository;

    private Logger logger = Logger.getLogger(MembershipsServiceImpl.class.getName());

    @Autowired
    public MembershipsServiceImpl(MembershipRepository repository) {
        this.repository = repository;
//        this.membershipsClient = membershipsClient;
    }

    @Override
    public List<MembershipDto> findAll() {
        List<Membership> memberships = repository.findAll();
        List<MembershipDto> membershipDtos = new ArrayList<>();
        memberships.forEach(t -> membershipDtos.add(MembershipDto.fromModel(t)));
        membershipDtos.stream().forEach(t -> t.add(linkTo(methodOn(MembershipsRestController.class).findById(t.getKey())).withSelfRel()));
        return membershipDtos;
    }

    @Override
    public MembershipDto findById(UUID membershipId) {
        logger.info("Finding membership by id " + membershipId);
        Membership membership = repository.findById(membershipId)
                .orElseThrow(() -> new ResourceNotFoundException(Membership.class, membershipId));
        MembershipDto membershipDto = MembershipDto.fromModel(membership);
        membershipDto.add(linkTo(methodOn(MembershipsRestController.class).findById(membershipId)).withSelfRel());
        return membershipDto;
    }

    @Override
    public MembershipDto create(MembershipDto dto) {
        logger.info("Creating membership");
        var membershipDto = MembershipDto.fromModel(repository.save(dto.toModel()));
        membershipDto.add(linkTo(methodOn(MembershipsRestController.class).findById(membershipDto.getKey())).withSelfRel());
        return membershipDto;
    }

    @Override
    public MembershipDto update(MembershipDto membershipDto) {
        if(membershipDto == null) throw new RequiredObjectsNullException();
        logger.info("Updating a Membership!");
        Membership membership = repository.findById(membershipDto.getKey())
                .orElseThrow(() -> new ResourceNotFoundException(Membership.class, membershipDto.getKey()));
        membership.setRole(membershipDto.getRole());
        membership.setUser(membershipDto.getUser());
        membership.setTeam(membershipDto.getTeam());
        var dto = MembershipDto.fromModel(repository.save(membership));
        dto.add(linkTo(methodOn(MembershipsRestController.class).findById(membershipDto.getKey())).withSelfRel());
        return dto;
    }

    @Override
    public void delete(UUID membershipId) {
        logger.info("Deleting membership with id " + membershipId);
        Membership membership = repository.findById(membershipId)
                .orElseThrow(() -> new ResourceNotFoundException(Membership.class, membershipId));
        repository.delete(membership);
    }
}
