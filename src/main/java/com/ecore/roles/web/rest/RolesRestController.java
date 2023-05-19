package com.ecore.roles.web.rest;

import com.ecore.roles.service.RolesService;
import com.ecore.roles.web.RolesApi;
import com.ecore.roles.web.dto.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/roles")
public class RolesRestController implements RolesApi {

    private final RolesService service;

    @GetMapping(value = "/find",
            produces = {"application/json"})
    @Operation(summary = "Finds a roles by User and Team Id", description = "Finds a roles by User and Team Id",
            tags = {"Roles", "roles"},
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = MembershipDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "201", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content),
            })
    public List<MembershipDto> findByUserIdAndTeamId(
            @RequestParam(value = "userId", defaultValue = "") UUID userId,
            @RequestParam(value = "teamId", defaultValue = "") UUID teamId) {
        return service.findByUserIdAndTeamId(userId, teamId);
    }

    @GetMapping(produces = {"application/json"})
    @Operation(summary = "Finds all Roles", description = "Finds all Roles",
            tags = {"Roles"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = RoleDto.class)))

                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content),
            }

    )
    public List<RoleDto> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = {"application/json"})
    @Operation(summary = "Finds a role by Id", description = "Finds role by Id",
            tags = {"Roles", "role"},
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = RoleDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "201", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content),
            }

    )
    public RoleDto findById(@PathVariable(value = "id") UUID id) {
        return service.findById(id);
    }

    @PostMapping(consumes = {"application/json"},
            produces = {"application/json"})
    @Operation(summary = "Add New role", description = "Add New role",
            tags = {"Roles"},
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = RoleDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content),
            }

    )
    public RoleDto save(@RequestBody RoleDto roleDto) {
        return service.create(roleDto);
    }

    @PutMapping(consumes = {"application/json"},
            produces = {"application/json"})
    @Operation(summary = "Update role", description = "Update role",
            tags = {"Roles"},
            responses = {
                    @ApiResponse(
                            description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = RoleDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content),
            }

    )
    public RoleDto update(@RequestBody RoleDto role) {
        return service.update(role);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete role", description = "Delete role",
            tags = {"Roles"},
            responses = {
                    @ApiResponse(
                            description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content),
            }

    )
    public ResponseEntity<Void> delete(@PathVariable(value = "id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
