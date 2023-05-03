package com.ecore.roles.service;

import com.ecore.roles.client.model.Team;
import com.ecore.roles.web.dto.*;

import java.util.List;
import java.util.UUID;

public interface TeamsService {

//    Team getTeam(UUID id);
//
//    List<Team> getTeams();

    List<TeamDto> findAll();
    TeamDto findById(UUID id);
    TeamDto create(TeamDto team);
    TeamDto update(TeamDto team);
    void delete(UUID id);
}
