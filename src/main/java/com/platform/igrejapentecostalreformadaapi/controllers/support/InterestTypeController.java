package com.platform.igrejapentecostalreformadaapi.controllers.support;

import com.platform.igrejapentecostalreformadaapi.data.vo.support.InterestTypeVO;
import com.platform.igrejapentecostalreformadaapi.services.support.InterestTypeService;
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
@RequestMapping(value = "/api/v1/interest-type")
@Tag(name = "InterestType", description = "Endpoints for Managing Interest Type")
public class InterestTypeController {

    @Autowired
    private InterestTypeService service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON})
    @Operation(
            summary = "Finds all Interest Type",
            description = "Service for find all the Interest Type",
            tags = {"InterestType"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = InterestTypeVO.class))
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
    public ResponseEntity<List<InterestTypeVO>> findAll() throws Exception {

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON}
    )
    @Operation(
            summary = "Find a Interest Type",
            description = "Service for find a Interest Type by id",
            tags = {"InterestType"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = InterestTypeVO.class))
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
    public ResponseEntity<InterestTypeVO> findById(@PathVariable Long id) {
        InterestTypeVO entityVO = service.findById(id);

        return ResponseEntity.ok().body(entityVO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(
            value = "/create",
            consumes = {MediaType.APPLICATION_JSON},
            produces = {MediaType.APPLICATION_JSON})
    @Operation(
            summary = "Create a Interest Type data",
            description = "Create Interest Type by passing in a JSON representation of product",
            tags = {"InterestType"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = InterestTypeVO.class))
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
    public ResponseEntity<InterestTypeVO> create(@RequestBody @Valid InterestTypeVO membershipType) {
        InterestTypeVO vo = service.create(membershipType);

        return ResponseEntity.ok().body(vo);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(
            value = "/update",
            consumes = {MediaType.APPLICATION_JSON},
            produces = {MediaType.APPLICATION_JSON})
    @Operation(
            summary = "Update a Interest Type data",
            description = "Update a Interest Type by passing in a JSON representation of product",
            tags = {"InterestType"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = InterestTypeVO.class))
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
    public ResponseEntity<InterestTypeVO> update(@RequestBody @Valid InterestTypeVO interestTypeVO) {
        InterestTypeVO updatedVo = service.update(interestTypeVO);

        return ResponseEntity.ok().body(updatedVo);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON}
    )
    @Operation(
            summary = "Delete a Interest Type",
            description = "Service for delete a Interest Type by id",
            tags = {"InterestType"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = InterestTypeVO.class))
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
    public ResponseEntity<InterestTypeVO> delete(@PathVariable Long id) {
        InterestTypeVO entityVO = service.delete(id);

        return ResponseEntity.ok().body(entityVO);
    }

}
