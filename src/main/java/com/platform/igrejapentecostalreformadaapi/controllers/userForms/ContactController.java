package com.platform.igrejapentecostalreformadaapi.controllers.userForms;

import com.platform.igrejapentecostalreformadaapi.data.vo.userForms.ContactVO;
import com.platform.igrejapentecostalreformadaapi.services.userForms.ContactService;
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

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api/v1/contacts")
@Tag(name = "Contact", description = "Endpoints for Managing Contacts")
public class ContactController {

    private final Logger logger = Logger.getLogger(ContactService.class.getName());

    @Autowired
    private ContactService service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON})
    @Operation(
            summary = "Finds all contacts",
            description = "Service for find all the contacts",
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
    public ResponseEntity<List<ContactVO>> findAll() throws Exception {

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON}
    )
    @Operation(
            summary = "Find a contact",
            description = "Service for find a contact by id",
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
    public ResponseEntity<ContactVO> findById(@PathVariable Long id) {
        ContactVO selectedContact = service.findById(id);

        return ResponseEntity.ok().body(selectedContact);
    }

    @PostMapping(
            value = "/create-contact-data/{userId}",
            consumes = {MediaType.APPLICATION_JSON},
            produces = {MediaType.APPLICATION_JSON})
    @Operation(
            summary = "Create a user contact data",
            description = "Create a user contact by passing in a JSON representation of product",
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
    public ResponseEntity<ContactVO> create(@RequestBody ContactVO contactVO, @PathVariable Long userId) {
     ContactVO contact = service.create(contactVO, userId);

     return ResponseEntity.ok().body(contact);
    }

    @PutMapping(
            value = "/update-contact-data",
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
    public ResponseEntity<ContactVO> update(@RequestBody ContactVO contactVO) {
     ContactVO contact = service.update(contactVO);

     return ResponseEntity.ok().body(contact);
    }

    @GetMapping(
            value = "/find-by-user-id/{id}",
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
      public ResponseEntity<ContactVO> findByUserId(@PathVariable(value = "id") Long id) throws Exception {

       return ResponseEntity.ok(service.findByUserId(id));
      }
}
