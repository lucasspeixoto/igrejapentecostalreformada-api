package com.platform.igrejapentecostalreformadaapi.controllers;
import com.platform.igrejapentecostalreformadaapi.data.vo.UserProcessVO;
import com.platform.igrejapentecostalreformadaapi.entities.UserProcess;
import com.platform.igrejapentecostalreformadaapi.services.ContactService;
import com.platform.igrejapentecostalreformadaapi.services.UserProcessService;
import com.platform.igrejapentecostalreformadaapi.utils.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api/v1/user-process")
@Tag(name = "User Process", description = "Endpoints for Managing User Process")
public class UserProcessController {

    private final Logger logger = Logger.getLogger(ContactService.class.getName());

    @Autowired
    private UserProcessService service;

    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON}
    )
    @Operation(
            summary = "Find a User process",
            description = "Service for find a contact by id",
            tags = {"User Process"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = UserProcessVO.class))
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
    public ResponseEntity<UserProcessVO> findById(@PathVariable Long id) {
     UserProcessVO selectedContact = service.findById(id);

        return ResponseEntity.ok().body(selectedContact);
    }

    @PostMapping(
            value = "/create",
            consumes = {MediaType.APPLICATION_JSON},
            produces = {MediaType.APPLICATION_JSON})
    @Operation(
            summary = "Create a user process data",
            description = "Create a user process by passing in a JSON representation of product",
            tags = {"User Process"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserProcessVO.class))
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
    public ResponseEntity<UserProcessVO> create(@RequestBody UserProcessVO userProcessVO) {
     UserProcessVO contact = service.create(userProcessVO);

     return ResponseEntity.ok().body(contact);
    }

    @PutMapping(
            value = "/update",
            consumes = {MediaType.APPLICATION_JSON},
            produces = {MediaType.APPLICATION_JSON})
    @Operation(
            summary = "Update a user contact data",
            description = "Update a user contact by passing in a JSON representation of product",
            tags = {"Contact"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserProcessVO.class))
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
    public ResponseEntity<UserProcessVO> update(@RequestBody UserProcessVO userProcessVO) {
     UserProcessVO userProcess = service.update(userProcessVO);

     return ResponseEntity.ok().body(userProcess);
    }

    @GetMapping(
            value = "/find-by-user-id/{id}",
            produces = {MediaType.APPLICATION_JSON})
    @Operation(
            summary = "Finds User process by user id",
            description = "Service for find user process by user id",
            tags = {"User Process"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = UserProcessVO.class))
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
    public ResponseEntity<UserProcessVO> findByUserId(@PathVariable(value = "id") Long id) throws Exception {

        return ResponseEntity.ok(service.findByUserId(id));
    }

    @PatchMapping(
            value = "/setHasContact/{id}/{userId}",
            consumes = {MediaType.APPLICATION_JSON},
            produces = {MediaType.APPLICATION_JSON})
    @Operation(
            summary = "Set user Has Contact Data",
            description = "Set user Has Contact Data by passing in a JSON representation of product",
            tags = {"Contact"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserProcess.class))
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
    public ResponseEntity<UserProcessVO> setHasContact(@PathVariable Long id, @PathVariable Long userId) {
        UserProcessVO userProcess = service.setHasContact(id, userId);

        return ResponseEntity.ok().body(userProcess);
    }
}
