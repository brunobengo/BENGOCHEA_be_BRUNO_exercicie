package com.ecore.roles.unittests.mapper.mocks;

import com.ecore.roles.model.*;
import com.ecore.roles.utils.*;
import com.ecore.roles.web.dto.*;

import java.util.*;

public class MockTeam {

    public static Team mockEntity() {
        return mockEntity(0);
    }

    public static TeamDto mockVO() {
        return mockVO(0);
    }

    public static List<Team> mockEntityList() {
        List<Team> teams = new ArrayList<Team>();
        for (int i = 0; i < 14; i++) {
            teams.add(mockEntity(i));
        }
        return teams;
    }

    public static List<TeamDto> mockVOList() {
        List<TeamDto> teamsDto = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            teamsDto.add(mockVO(i));
        }
        return teamsDto;
    }

    public static Team mockEntity(int number) {
        Team team = new Team();
        team.setId(UUID.randomUUID());
        team.setName("Team Test" + number);
        return team;
    }

    public static Team mockEntity(UUID uuid) {
        Team team = new Team();
        team.setId(uuid);
        team.setName("Team Test" + uuid);
        return team;
    }

    public static TeamDto mockVO(Integer number) {
        TeamDto teamDto =
                new TeamDto(TestData.ORDINARY_CORAL_LYNX_TEAM_UUID, "Team Test" + number, TestData.UUID_4);
        return teamDto;
    }

    public static TeamDto mockVO(UUID uuid) {
        TeamDto teamDto = new TeamDto(uuid, "Team Test", TestData.UUID_4);
        return teamDto;
    }

}
