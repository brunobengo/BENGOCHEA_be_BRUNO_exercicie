package com.ecore.roles.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.ecore.roles.exception.*;
import com.ecore.roles.model.*;
import com.ecore.roles.web.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecore.roles.repository.UserRepository;
import com.ecore.roles.unittests.mapper.mocks.*;

import java.util.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    MockUser input;

    @InjectMocks
    private UsersServiceImpl service;

    @Mock
    UserRepository repository;

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockUser();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {

        List<User> list = input.mockEntityList();

        when(repository.findAll()).thenReturn(list);

        var people = service.findAll();

        assertNotNull(people);
        assertEquals(14, people.size());

        var personOne = people.get(0);

        assertNotNull(personOne);
        assertNotNull(personOne.getKey());
        assertNotNull(personOne.getLinks());

        assertTrue(personOne.toString()
                .contains("links: [</v1/users/" + personOne.getKey() + ">;rel=\"self\"]"));
        assertEquals("First Name Test0", personOne.getFirstName());
        assertEquals("Last Name Test0", personOne.getLastName());
        assertEquals("Display Name Test0", personOne.getDisplayName());
        assertEquals("Avatar Url0", personOne.getAvatarUrl());
        assertEquals("Location0", personOne.getLocation());

    }

    @Test
    void testFindById() {
        UUID uuid = UUID.randomUUID();
        User entity = input.mockEntity(uuid);

        when(repository.findById(uuid)).thenReturn(Optional.of(entity));

        var result = service.findById(uuid);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</v1/users/" + uuid + ">;rel=\"self\"]"));
        assertEquals("First Name Test", result.getFirstName());
        assertEquals("Last Name Test", result.getLastName());
        assertEquals("Display Name Test", result.getDisplayName());
        assertEquals("Avatar Url", result.getAvatarUrl());
        assertEquals("Location", result.getLocation());
    }

    @Test
    void testSave() {
        UUID userId = UUID.randomUUID();
        UserDto userDto = input.mockVO(userId);
        User user = userDto.toModel();
        when(repository.save(any())).thenReturn(user);

        UserDto createdUserDto = service.save(userDto);

        assertEquals("First Name Test", createdUserDto.getFirstName());
        assertEquals("Last Name Test", createdUserDto.getLastName());
        assertEquals("Display Name Test", createdUserDto.getDisplayName());
        assertEquals("Avatar Url", createdUserDto.getAvatarUrl());
        assertEquals("Location", createdUserDto.getLocation());
        assertTrue(createdUserDto.toString()
                .contains("links: [</v1/users/" + createdUserDto.getKey() + ">;rel=\"self\"]"));
    }

    @Test
    void testUpdate() {
        UUID uuid = UUID.randomUUID();

        User entity = input.mockEntity(uuid);

        User persisted = input.mockEntity(uuid);
        UserDto userDto = input.mockVO(uuid);

        when(repository.findById(uuid)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(userDto);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</v1/users/" + uuid + ">;rel=\"self\"]"));
        assertEquals("First Name Test", result.getFirstName());
        assertEquals("Last Name Test", result.getLastName());
        assertEquals("Display Name Test", result.getDisplayName());
        assertEquals("Avatar Url", result.getAvatarUrl());
        assertEquals("Location", result.getLocation());
    }

    @Test
    void testSaveWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectsNullException.class,
                () -> {
                    service.save(null);
                });
        String expectedMessage = "It`s not allowed to persist a null object!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void testUpdateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectsNullException.class,
                () -> {
                    service.update(null);
                });
        String expectedMessage = "It`s not allowed to persist a null object!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

}
