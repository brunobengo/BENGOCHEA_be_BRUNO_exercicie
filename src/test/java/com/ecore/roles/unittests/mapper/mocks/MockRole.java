package com.ecore.roles.unittests.mapper.mocks;

import com.ecore.roles.model.*;
import com.ecore.roles.utils.*;
import com.ecore.roles.web.dto.*;

import java.util.*;

public class MockRole {

    public static Role mockEntity() {
        return mockEntity(0);
    }

    public static RoleDto mockVO() {
        return mockVO(0);
    }

    public static List<Role> mockEntityList() {
        List<Role> roles = new ArrayList<Role>();
        for (int i = 0; i < 14; i++) {
            roles.add(mockEntity(i));
        }
        return roles;
    }

    public static List<RoleDto> mockVOList() {
        List<RoleDto> rolesDto = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            rolesDto.add(mockVO(i));
        }
        return rolesDto;
    }

    public static Role mockEntity(int number) {
        Role role = new Role();
        role.setId(UUID.randomUUID());
        role.setName("Role Test" + number);
        return role;
    }

    public static Role mockEntity(UUID uuid) {
        Role role = new Role();
        role.setId(uuid);
        role.setName("Role Test" + uuid);
        return role;
    }

    public static RoleDto mockVO(Integer number) {
        RoleDto roleDto = new RoleDto();
        roleDto.setName("Role Test");
        return roleDto;
    }

    public static RoleDto mockVO(UUID uuid) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(uuid);
        roleDto.setName("Role Test");
        return roleDto;
    }

}
