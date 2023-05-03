package com.ecore.roles.web.rest;

import com.ecore.roles.client.*;
import com.ecore.roles.service.UsersService;
import com.ecore.roles.web.UsersApi;
import com.ecore.roles.web.dto.UserDto;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.ecore.roles.web.dto.UserDto.fromModel;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/users")
public class UsersRestController implements UsersApi {

    private final UsersService usersService;

    @GetMapping(produces = {"application/json"})
    @Operation(summary = "Finds all Users", description = "Finds all Users",
            tags = {"Users"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = UserDto.class))
                                    )

                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content),
            }

    )
    public List<UserDto> findAll() {
        return usersService.findAll();
    }

    @GetMapping(value = "/{id}", produces = {"application/json"})
    @Operation(summary = "Finds a user by Id", description = "Finds user by Id",
            tags = {"Users", "user"},
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserDto.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "201", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content),
            }

    )
    public UserDto findById(@PathVariable(value = "id") UUID id) {
        return usersService.findById(id);
    }

    @PostMapping(consumes = {"application/json"},
            produces = {"application/json"})
    @Operation(summary = "Add New user", description = "Add New user",
            tags = {"Users"},
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content),
            }

    )
    public UserDto save(@RequestBody UserDto userDto) {
        return usersService.save(userDto);
    }

    @PutMapping(consumes = {"application/json"},
            produces = {"application/json"})
    @Operation(summary = "Update user", description = "Update user",
            tags = {"Users"},
            responses = {
                    @ApiResponse(
                            description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content),
            }

    )
    public UserDto update(@RequestBody UserDto user) {
        return usersService.update(user);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete user", description = "Delete user",
            tags = {"Users"},
            responses = {
                    @ApiResponse(
                            description = "No Content", responseCode = "204", content = @Content
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content),
            }

    )
    public ResponseEntity<?> delete(@PathVariable(value = "id") UUID id) {
        usersService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
