package com.ecore.roles.service;

import com.ecore.roles.exception.ResourceNotFoundException;
import com.ecore.roles.repository.MembershipRepository;
import com.ecore.roles.repository.RoleRepository;
import com.ecore.roles.service.impl.RolesServiceImpl;
import com.ecore.roles.web.dto.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.ecore.roles.utils.TestData.UUID_1;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class RolesServiceTest {

    @InjectMocks
    private RolesServiceImpl rolesService;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private MembershipRepository membershipRepository;

    @Mock
    private MembershipsService membershipsService;

    @Test
    void shouldFailToCreateRoleWhenRoleIsNull() {



        assertThrows(NullPointerException.class,
                () -> rolesService.create(RoleDto.fromModel(null)).toModel());
    }

    // @Test
    // public void shouldReturnRoleWhenRoleIdExists() {
    // Role developerRole = DEVELOPER_ROLE();
    // when(roleRepository.findById(developerRole.getId())).thenReturn(Optional.of(developerRole));
    //
    // Role role = rolesService.findById(developerRole.getId()).toModel();
    //
    // assertNotNull(role);
    // assertEquals(developerRole, role);
    // }

    @Test
    void shouldFailToGetRoleWhenRoleIdDoesNotExist() {
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> rolesService.findById(UUID_1).toModel());

        assertEquals(format("Role %s not found", UUID_1), exception.getMessage());
    }
}
