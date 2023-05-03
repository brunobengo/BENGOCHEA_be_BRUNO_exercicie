package com.ecore.roles.service;

import com.ecore.roles.exception.*;
import com.ecore.roles.model.User;
import com.ecore.roles.web.dto.*;

import java.util.List;
import java.util.UUID;

public interface UsersService {

    public List<UserDto> findAll();
    public UserDto findById(UUID key);
    public UserDto save(UserDto user);
    public UserDto update(UserDto userToUpdate);
    public void delete (UUID key);
}
