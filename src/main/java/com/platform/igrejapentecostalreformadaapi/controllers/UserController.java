package com.platform.igrejapentecostalreformadaapi.controllers;

import com.platform.igrejapentecostalreformadaapi.data.vo.UserVO;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import com.platform.igrejapentecostalreformadaapi.services.UserService;
import com.platform.igrejapentecostalreformadaapi.utils.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user")
@Tag(name = "User", description = "Endpoints for Manage User data")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/data/{usernameOrEmail}")
    @Operation(
            summary = "Get User Data",
            description = "Service for get user data",
            tags = {"User"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = User.class))
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
    public ResponseEntity<UserVO> getUserData(@PathVariable(value = "usernameOrEmail") String usernameOrEmail) {
        UserVO userVO = this.userService.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

        return ResponseEntity.ok(userVO);
    }

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON}
    )
    @Operation(
            summary = "Get All Users",
            description = "Service for get all users",
            tags = {"User"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = User.class))
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
    public ResponseEntity<List<UserVO>> findAll() {
        List<UserVO> users = this.userService.findAll();

        return ResponseEntity.ok(users);
    }

}
