package com.ecore.roles.unittests.mapper.mocks;

import com.ecore.roles.model.*;
import com.ecore.roles.web.dto.*;

import java.util.*;

public class MockUser {


    public static User mockEntity() {
        return mockEntity(0);
    }

    public static UserDto mockVO() {
        return mockVO(0);
    }

    public static List<User> mockEntityList() {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 14; i++) {
            users.add(mockEntity(i));
        }
        return users;
    }

    public static List<UserDto> mockVOList() {
        List<UserDto> usersDto = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            usersDto.add(mockVO(i));
        }
        return usersDto;
    }

    public static User mockEntity(int number) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setFirstName("First Name Test" + number);
        user.setLastName("Last Name Test" + number);
        user.setDisplayName("Display Name Test" + number);
        user.setAvatarUrl("Avatar Url" + number);
        user.setLocation("Location" + number);
        return user;
    }
    public static User mockEntity(UUID uuid) {
        User user = new User();
        user.setId(uuid);
        user.setFirstName("First Name Test");
        user.setLastName("Last Name Test");
        user.setDisplayName("Display Name Test");
        user.setAvatarUrl("Avatar Url");
        user.setLocation("Location");
        return user;
    }

    public static UserDto mockVO(Integer number) {
        UserDto userDto = new UserDto();
        userDto.setFirstName("First Name Test" + number);
        userDto.setLastName("Last Name Test" + number);
        userDto.setDisplayName("Display Name Test" + number);
        userDto.setAvatarUrl("Avatar Url" + number);
        userDto.setLocation("Location" + number);
        return userDto;
    }

    public static UserDto mockVO(UUID uuid) {
        UserDto userDto = new UserDto();
        userDto.setKey(uuid);
        userDto.setFirstName("First Name Test");
        userDto.setLastName("Last Name Test");
        userDto.setDisplayName("Display Name Test");
        userDto.setAvatarUrl("Avatar Url");
        userDto.setLocation("Location");
        return userDto;
    }

}

