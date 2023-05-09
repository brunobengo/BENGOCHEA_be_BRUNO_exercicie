package com.ecore.roles.service.impl;

import com.ecore.roles.exception.*;
import com.ecore.roles.model.*;
import com.ecore.roles.repository.*;
import com.ecore.roles.service.TeamsService;
import com.ecore.roles.web.dto.*;
import com.ecore.roles.web.rest.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class TeamsServiceImpl implements TeamsService {
        private final TeamRepository repository;

    private Logger logger = Logger.getLogger(TeamsServiceImpl.class.getName());

    @Autowired
    public TeamsServiceImpl(TeamRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TeamDto> findAll() {
        List<Team> teams = repository.findAll();
        List<TeamDto> teamDtos = new ArrayList<>();
        teams.forEach(t -> teamDtos.add(TeamDto.fromModel(t)));
        teamDtos.stream().forEach(
                t -> t.add(linkTo(methodOn(TeamsRestController.class).findById(t.getId())).withSelfRel()));
        return teamDtos;
    }

    @Override
    public TeamDto findById(UUID teamId) {
        String info = "Finding team by id " + teamId;
        logger.info(info);
        Team team = repository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException(Team.class, teamId));
        TeamDto teamDto = TeamDto.fromModel(team);
        teamDto.add(linkTo(methodOn(TeamsRestController.class).findById(teamId)).withSelfRel());
        return teamDto;
    }

    @Override
    public TeamDto create(TeamDto teamDto) {
        logger.info("Creating team " + teamDto.getName());
        var dto = TeamDto.fromModel(repository.save(teamDto.toModel()));
        dto.add(linkTo(methodOn(TeamsRestController.class).findById(dto.getId())).withSelfRel());
        return dto;
    }

    @Override
    public TeamDto update(TeamDto teamDto) {
        if (teamDto == null)
            throw new RequiredObjectsNullException();
        logger.info("Updating a Team!");
        Team team = repository.findById(teamDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(Team.class, teamDto.getId()));
        team.setName(teamDto.getName());
        team.setTeamLeadId(teamDto.getTeamLeadId());
        var dto = TeamDto.fromModel(repository.save(team));
        dto.add(linkTo(methodOn(TeamsRestController.class).findById(teamDto.getId())).withSelfRel());
        return dto;
    }

    @Override
    public void delete(UUID teamId) {
        String info = "Deleting team with id " + teamId;
        logger.info(info);
        Team team = repository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException(Team.class, teamId));
        repository.delete(team);
    }
}
