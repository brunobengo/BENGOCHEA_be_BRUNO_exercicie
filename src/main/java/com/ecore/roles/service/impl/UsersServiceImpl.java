package com.ecore.roles.service.impl;

import com.ecore.roles.exception.*;
import com.ecore.roles.model.*;
import com.ecore.roles.repository.UserRepository;
import com.ecore.roles.service.UsersService;
import com.ecore.roles.web.dto.*;
import com.ecore.roles.web.rest.*;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.*;

@Service
public class UsersServiceImpl implements UsersService {

    private Logger logger = Logger.getLogger(UsersServiceImpl.class.getName());

    @Autowired
    private UserRepository repository;

    @Override
    public List<UserDto> findAll() {
        logger.info("Finding all Users!");
        var users = repository.findAll();
        List<UserDto> listUsers = new ArrayList<>();
        users.forEach(u -> listUsers.add(UserDto.fromModel(u)));
        listUsers.stream().forEach(
                u -> u.add(linkTo(methodOn(UsersRestController.class).findById(u.getKey())).withSelfRel()));
        return listUsers;
    }

    @Override
    public UserDto findById(UUID id) {
        String info = "Finding by id " + id;
        logger.info(info);
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(User.class, id));
        UserDto userDto = UserDto.fromModel(user);
        userDto.add(linkTo(methodOn(UsersRestController.class).findById(id)).withSelfRel());
        return userDto;
    }

    @Override
    public UserDto save(UserDto userDto) {
        if (userDto == null)
            throw new RequiredObjectsNullException();
        logger.info("Creating a new User!");
        var result = UserDto.fromModel(repository.save(userDto.toModel()));
        result.add(linkTo(methodOn(UsersRestController.class).findById(result.getKey())).withSelfRel());
        return result;
    }

    @Override
    public UserDto update(UserDto dto) {
        if (dto == null)
            throw new RequiredObjectsNullException();
        logger.info("Updating a User!");
        User user = repository.findById(dto.getKey())
                .orElseThrow(() -> new ResourceNotFoundException(User.class, dto.getKey()));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setDisplayName(dto.getDisplayName());
        user.setAvatarUrl(dto.getAvatarUrl());
        user.setLocation(dto.getLocation());
        var userDto = UserDto.fromModel(repository.save(user));
        userDto.add(linkTo(methodOn(UsersRestController.class).findById(userDto.getKey())).withSelfRel());
        return userDto;
    }

    @Override
    public void delete(UUID id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(User.class, id));
        repository.delete(user);
    }
}
