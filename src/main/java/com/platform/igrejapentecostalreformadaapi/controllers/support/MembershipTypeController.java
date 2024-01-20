package com.platform.igrejapentecostalreformadaapi.controllers.support;

import com.platform.igrejapentecostalreformadaapi.data.vo.support.MembershipTypeVO;
import com.platform.igrejapentecostalreformadaapi.services.support.MembershipTypeService;
import com.platform.igrejapentecostalreformadaapi.utils.constants.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/membership-type")
@Tag(name = "MembershipType", description = "Endpoints for Managing Membership Type")
public class MembershipTypeController {

    @Autowired
    private MembershipTypeService service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON})
    @Operation(
            summary = "Finds all Membership Type",
            description = "Service for find all the Membership Type",
            tags = {"MembershipType"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = MembershipTypeVO.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<List<MembershipTypeVO>> findAll() throws Exception {

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON}
    )
    @Operation(
            summary = "Find a Membership Type",
            description = "Service for find a Membership Type by id",
            tags = {"MembershipType"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = MembershipTypeVO.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<MembershipTypeVO> findById(@PathVariable Long id) {
        MembershipTypeVO entityVO = service.findById(id);

        return ResponseEntity.ok().body(entityVO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(
            value = "/create",
            consumes = {MediaType.APPLICATION_JSON},
            produces = {MediaType.APPLICATION_JSON})
    @Operation(
            summary = "Create a Membership Type data",
            description = "Create Membership Type by passing in a JSON representation of product",
            tags = {"MembershipType"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = MembershipTypeVO.class))
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<MembershipTypeVO> create(@RequestBody @Valid MembershipTypeVO membershipType) {
        MembershipTypeVO vo = service.create(membershipType);

        return ResponseEntity.ok().body(vo);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(
            value = "/update",
            consumes = {MediaType.APPLICATION_JSON},
            produces = {MediaType.APPLICATION_JSON})
    @Operation(
            summary = "Update a membership Type data",
            description = "Update a membership Type by passing in a JSON representation of product",
            tags = {"MembershipType"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = MembershipTypeVO.class))
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<MembershipTypeVO> update(@RequestBody @Valid MembershipTypeVO membershipTypeVO) {
        MembershipTypeVO updatedVo = service.update(membershipTypeVO);

        return ResponseEntity.ok().body(updatedVo);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON}
    )
    @Operation(
            summary = "Delete a membership type",
            description = "Service for delete a membership type by id",
            tags = {"MembershipType"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = MembershipTypeVO.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<MembershipTypeVO> delete(@PathVariable Long id) {
        MembershipTypeVO entityVO = service.delete(id);

        return ResponseEntity.ok().body(entityVO);
    }

}
