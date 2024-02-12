package com.platform.igrejapentecostalreformadaapi.controllers.register;

import com.platform.igrejapentecostalreformadaapi.data.vo.register.ContactVO;
import com.platform.igrejapentecostalreformadaapi.utils.constants.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Contact", description = "Endpoints for Managing Contacts")
public interface ContactController {

    /**
     * Creates a new contact for the specified user.
     *
     * @param contactVO the contact information to create
     * @param userId    the ID of the user to create the contact for
     * @return the created contact information
     *
     * @author Lucas Peixoto
     */
    @PostMapping(value = "/create/{userId}", consumes = {MediaType.APPLICATION_JSON}, produces = {MediaType.APPLICATION_JSON})
    @Operation(summary = "Create a user contact data", description = "Create a user contact by passing in a JSON representation of contact", tags = {"Contact"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = ContactVO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    ResponseEntity<ContactVO> create(@RequestBody @Valid ContactVO contactVO, @PathVariable Long userId);

    /**
     * Service for find all the contacts
     *
     * @author Lucas Peixoto
     */
    @GetMapping(produces = {MediaType.APPLICATION_JSON})
    @Operation(summary = "Finds all contacts", description = "Service for find all the contacts", tags = {"Contact"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ContactVO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    ResponseEntity<List<ContactVO>> findAll() throws Exception;

    /**
     * Service for find a contact by id
     *
     * @param id the id of the contact to find
     * @return the contact with the specified id, or an error response if the contact could not be found
     *
     * @author Lucas Peixoto
     */
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON})
    @Operation(summary = "Find a contact", description = "Service for find a contact by id", tags = {"Contact"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ContactVO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    ResponseEntity<ContactVO> findById(@PathVariable Long id);


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
                            content = @Content(schema = @Schema(implementation = ContactVO.class))
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
    ResponseEntity<ContactVO> update(@RequestBody @Valid ContactVO contactVO);

    @GetMapping(
            value = "/get-by-user-id",
            produces = {MediaType.APPLICATION_JSON})
    @Operation(
            summary = "Finds Contact data by user id",
            description = "Service for find contact by user id",
            tags = {"Contact"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = ContactVO.class))
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
    ResponseEntity<ContactVO> findByUserId(@RequestParam(value = "userId") Long userId) throws Exception;
}
