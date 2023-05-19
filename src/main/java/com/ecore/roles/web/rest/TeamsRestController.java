package com.ecore.roles.web.rest;

import com.ecore.roles.service.TeamsService;
import com.ecore.roles.web.TeamsApi;
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
@RequestMapping(value = "/v1/teams")
public class TeamsRestController implements TeamsApi {

    private final TeamsService service;

    @GetMapping(produces = {"application/json"})
    @Operation(summary = "Finds all Teams", description = "Finds all Teams",
            tags = {"Teams"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = TeamDto.class)))

                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content),
            }

    )
    public List<TeamDto> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = {"application/json"})
    @Operation(summary = "Finds a team by Id", description = "Finds team by Id",
            tags = {"Teams", "team"},
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TeamDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "201", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content),
            }

    )
    public TeamDto findById(@PathVariable(value = "id") UUID id) {
        return service.findById(id);
    }

    @PostMapping(consumes = {"application/json"},
            produces = {"application/json"})
    @Operation(summary = "Add New team", description = "Add New team",
            tags = {"Teams"},
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TeamDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content),
            }

    )
    public TeamDto save(@RequestBody TeamDto teamDto) {
        return service.create(teamDto);
    }

    @PutMapping(consumes = {"application/json"},
            produces = {"application/json"})
    @Operation(summary = "Update team", description = "Update team",
            tags = {"Teams"},
            responses = {
                    @ApiResponse(
                            description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TeamDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content),
            }

    )
    public TeamDto update(@RequestBody TeamDto team) {
        return service.update(team);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete team", description = "Delete team",
            tags = {"Teams"},
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
