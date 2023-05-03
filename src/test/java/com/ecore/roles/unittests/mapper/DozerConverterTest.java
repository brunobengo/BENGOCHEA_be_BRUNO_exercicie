package com.ecore.roles.unittests.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ecore.roles.model.*;
import com.ecore.roles.unittests.mapper.mocks.*;
import com.ecore.roles.web.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class DozerConverterTest {
    
    MockUser inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockUser();
    }

    @Test
    public void parseEntityToVOTest() {
        UserDto output = UserDto.fromModel(inputObject.mockEntity());
//        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("First Name Test0", output.getFirstName());
        assertEquals("Last Name Test0", output.getLastName());
        assertEquals("Display Name Test0", output.getDisplayName());
        assertEquals("Avatar Url0", output.getAvatarUrl());
        assertEquals("Location0", output.getLocation());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<UserDto> outputList = new ArrayList<>();
        var users = inputObject.mockEntityList();
        users.forEach(u -> outputList.add(UserDto.fromModel(u)));
        UserDto outputZero = outputList.get(0);
        
//        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("First Name Test0", outputZero.getFirstName());
        assertEquals("Last Name Test0", outputZero.getLastName());
        assertEquals("Display Name Test0", outputZero.getDisplayName());
        assertEquals("Avatar Url0", outputZero.getAvatarUrl());
        assertEquals("Location0", outputZero.getLocation());
        
        UserDto outputSeven = outputList.get(7);

        assertEquals("First Name Test7", outputSeven.getFirstName());
        assertEquals("Last Name Test7", outputSeven.getLastName());
        assertEquals("Display Name Test7", outputSeven.getDisplayName());
        assertEquals("Avatar Url7", outputSeven.getAvatarUrl());
        assertEquals("Location7", outputSeven.getLocation());;
        
        UserDto outputTwelve = outputList.get(12);

        assertEquals("First Name Test12", outputTwelve.getFirstName());
        assertEquals("Last Name Test12", outputTwelve.getLastName());
        assertEquals("Display Name Test12", outputTwelve.getDisplayName());
        assertEquals("Avatar Url12", outputTwelve.getAvatarUrl());
        assertEquals("Location12", outputTwelve.getLocation());;
    }

    @Test
    public void parseVOToEntityTest() {
        User output = inputObject.mockVO().toModel();
//        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("First Name Test0", output.getFirstName());
        assertEquals("Last Name Test0", output.getLastName());
        assertEquals("Display Name Test0", output.getDisplayName());
        assertEquals("Avatar Url0", output.getAvatarUrl());
        assertEquals("Location0", output.getLocation());;
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<User> outputList = new ArrayList<>();
        var userDto = inputObject.mockVOList();
        userDto.forEach(u -> outputList.add(u.toModel()));

        User outputZero = outputList.get(0);

        assertEquals("First Name Test0", outputZero.getFirstName());
        assertEquals("Last Name Test0", outputZero.getLastName());
        assertEquals("Display Name Test0", outputZero.getDisplayName());
        assertEquals("Avatar Url0", outputZero.getAvatarUrl());
        assertEquals("Location0", outputZero.getLocation());;
        
        User outputSeven = outputList.get(7);

        assertEquals("First Name Test7", outputSeven.getFirstName());
        assertEquals("Last Name Test7", outputSeven.getLastName());
        assertEquals("Display Name Test7", outputSeven.getDisplayName());
        assertEquals("Avatar Url7", outputSeven.getAvatarUrl());
        assertEquals("Location7", outputSeven.getLocation());;
        
        User outputTwelve = outputList.get(12);

        assertEquals("First Name Test12", outputTwelve.getFirstName());
        assertEquals("Last Name Test12", outputTwelve.getLastName());
        assertEquals("Display Name Test12", outputTwelve.getDisplayName());
        assertEquals("Avatar Url12", outputTwelve.getAvatarUrl());
        assertEquals("Location12", outputTwelve.getLocation());;
    }
}
