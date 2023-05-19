package com.ecore.roles.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ecore.roles.model.*;
import com.ecore.roles.repository.*;
import com.ecore.roles.service.*;
import com.ecore.roles.unittests.mapper.mocks.*;
import com.ecore.roles.web.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TeamsServiceImplTest {

    @Mock
    private TeamRepository repository;

    @InjectMocks
    private TeamsServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new TeamsServiceImpl(repository);
    }

    @Test
    void testFindAll() {
        List<Team> teams = new ArrayList<>();
        teams.add(new Team(UUID.randomUUID(), "Team 1", UUID.randomUUID()));
        teams.add(new Team(UUID.randomUUID(), "Team 2", UUID.randomUUID()));
        when(repository.findAll()).thenReturn(teams);

        List<TeamDto> teamDtos = service.findAll();

        assertEquals(2, teamDtos.size());
        assertEquals("Team 1", teamDtos.get(0).getName());
        assertEquals("Team 2", teamDtos.get(1).getName());
        // assertEquals("http://localhost/teams/" + teams.get(0).getId(),
        // teamDtos.get(0).getLink(Link.REL_SELF).get().getHref());
        assertTrue(teamDtos.get(0).toString()
                .contains("links: [</v1/teams/" + teamDtos.get(0).getId() + ">;rel=\"self\"]"));
    }

    @Test
    void testFindById() {
        UUID teamId = UUID.randomUUID();
        Team team = new Team(teamId, "Team 1", UUID.randomUUID());
        when(repository.findById(teamId)).thenReturn(Optional.of(team));

        TeamDto teamDto = service.findById(teamId);

        assertEquals("Team 1", teamDto.getName());
        assertTrue(teamDto.toString().contains("links: [</v1/teams/" + teamDto.getId() + ">;rel=\"self\"]"));
        // assertEquals("http://localhost/teams/" + teamId, teamDto.getLink(Link.REL_SELF).get().getHref());
    }

    @Test
    void testCreate() {
        UUID teamId = UUID.randomUUID();
        TeamDto teamDto = new TeamDto(teamId, "Team 1", UUID.randomUUID());
        Team team = teamDto.toModel();
        when(repository.save(any())).thenReturn(team);

        TeamDto createdTeamDto = service.create(teamDto);

        assertEquals("Team 1", createdTeamDto.getName());
        assertTrue(createdTeamDto.toString()
                .contains("links: [</v1/teams/" + createdTeamDto.getId() + ">;rel=\"self\"]"));

    }

    @Test
    void testUpdate() {
        UUID teamId = UUID.randomUUID();
        TeamDto teamDto = new TeamDto(teamId, "Team 1", UUID.randomUUID());
        Team team = teamDto.toModel();
        when(repository.findById(teamId)).thenReturn(Optional.of(team));
        when(repository.save(any())).thenReturn(team);

        TeamDto updatedTeamDto = service.update(teamDto);

        assertEquals("Team 1", updatedTeamDto.getName());
        assertTrue(updatedTeamDto.toString()
                .contains("links: [</v1/teams/" + updatedTeamDto.getId() + ">;rel=\"self\"]"));
    }
}
