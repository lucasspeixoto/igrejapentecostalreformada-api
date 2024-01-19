package com.platform.igrejapentecostalreformadaapi.controllers.register;

import com.platform.igrejapentecostalreformadaapi.data.vo.register.BaptismVO;
import com.platform.igrejapentecostalreformadaapi.services.register.BaptismService;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/baptism")
@Tag(name = "Baptism", description = "Endpoints for Managing Baptism")
public class BaptismController {

    @Autowired
    private BaptismService service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON})
    @Operation(
            summary = "Finds all family",
            description = "Service for find all the Baptism data",
            tags = {"Baptism"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = BaptismVO.class))
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
    public ResponseEntity<List<BaptismVO>> findAll() throws Exception {

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON}
    )
    @Operation(
            summary = "Find a Baptism",
            description = "Service for find a Baptism by id",
            tags = {"Baptism"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = BaptismVO.class))
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
    public ResponseEntity<BaptismVO> findById(@PathVariable Long id) {
        BaptismVO selectedVo = service.findById(id);

        return ResponseEntity.ok().body(selectedVo);
    }

    @PostMapping(
            value = "/create/{userId}",
            consumes = {MediaType.APPLICATION_JSON},
            produces = {MediaType.APPLICATION_JSON})
    @Operation(
            summary = "Create a user Baptism data",
            description = "Create a user Baptism by passing in a JSON representation of product",
            tags = {"Baptism"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BaptismVO.class))
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
    public ResponseEntity<BaptismVO> create(@RequestBody @Valid BaptismVO baptismVO, @PathVariable Long userId) {
        BaptismVO contact = service.create(baptismVO, userId);

        return ResponseEntity.ok().body(contact);
    }

    @PutMapping(
            value = "/update",
            consumes = {MediaType.APPLICATION_JSON},
            produces = {MediaType.APPLICATION_JSON})
    @Operation(
            summary = "Update a user Baptism data",
            description = "Update a user Baptism by passing in a JSON representation of product",
            tags = {"Baptism"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BaptismVO.class))
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
    public ResponseEntity<BaptismVO> update(@RequestBody @Valid BaptismVO baptismVO) {
        BaptismVO baptism = service.update(baptismVO);

        return ResponseEntity.ok().body(baptism);
    }

    @GetMapping(
            value = "/get-by-user-id",
            produces = {MediaType.APPLICATION_JSON})
    @Operation(
            summary = "Finds Baptism data by user id",
            description = "Service for find Baptism by user id",
            tags = {"Baptism"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = BaptismVO.class))
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
    public ResponseEntity<BaptismVO> findByUserId(@RequestParam(value = "userId") Long userId) throws Exception {

        return ResponseEntity.ok(service.findByUserId(userId));
    }
}
