package com.ecore.roles.web.rest;

import com.ecore.roles.service.*;
import com.ecore.roles.web.MembershipsApi;
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
@RequestMapping(value = "/v1/roles/memberships")
public class MembershipsRestController implements MembershipsApi {

    private final MembershipsService service;

    @GetMapping(produces = {"application/json"})
    @Operation(summary = "Finds all Memberships", description = "Finds all Memberships",
            tags = {"Memberships"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = MembershipDto.class)))

                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content),
            }

    )
    public List<MembershipDto> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = {"application/json"})
    @Operation(summary = "Finds a membership by Id", description = "Finds membership by Id",
            tags = {"Memberships", "membership"},
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = MembershipDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "201", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content),
            }

    )
    public MembershipDto findById(@PathVariable(value = "id") UUID id) {
        return service.findById(id);
    }

    @PostMapping(consumes = {"application/json"},
            produces = {"application/json"})
    @Operation(summary = "Add New membership", description = "Add New membership",
            tags = {"Memberships"},
            responses = {
                    @ApiResponse(
                            description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = MembershipDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content),
            }

    )
    public MembershipDto save(@RequestBody MembershipDto membershipDto) {
        return service.create(membershipDto);
    }

    @PutMapping(consumes = {"application/json"},
            produces = {"application/json"})
    @Operation(summary = "Update membership", description = "Update membership",
            tags = {"Memberships"},
            responses = {
                    @ApiResponse(
                            description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = MembershipDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content),
            }

    )
    public MembershipDto update(@RequestBody MembershipDto membership) {
        return service.update(membership);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete membership", description = "Delete membership",
            tags = {"Memberships"},
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
