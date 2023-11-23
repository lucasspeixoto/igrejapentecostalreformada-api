package com.platform.igrejapentecostalreformadaapi.controllers;


import com.platform.igrejapentecostalreformadaapi.data.response.JWTAuthResponse;
import com.platform.igrejapentecostalreformadaapi.data.response.RegisterResponse;
import com.platform.igrejapentecostalreformadaapi.data.vo.LoginVO;
import com.platform.igrejapentecostalreformadaapi.data.vo.RegisterVO;
import com.platform.igrejapentecostalreformadaapi.services.AuthService;
import com.platform.igrejapentecostalreformadaapi.services.UserService;
import com.platform.igrejapentecostalreformadaapi.utils.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON)
@Tag(name = "Authentication", description = "Endpoints for Login and Register")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping(value = {"/login", "/signin"})
    @Operation(
            summary = "Login in the app",
            description = "Service login the user",
            tags = {"Authentication"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = JWTAuthResponse.class))
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
    public ResponseEntity<JWTAuthResponse> login(@Valid @RequestBody LoginVO loginVO) {
        String token = authService.login(loginVO);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();

        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    // Register Rest API
    @PostMapping(value = {"/register", "/signup"})
    @Operation(
            summary = "Register in the app",
            description = "Service for create a new user",
            tags = {"Authentication"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = RegisterResponse.class))
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
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterVO registerVO) {
        String response = authService.register(registerVO);

        RegisterResponse registerResponse = new RegisterResponse();

        registerResponse.setStatus(201);
        registerResponse.setMessage("User created successfully");


        return ResponseEntity.ok(registerResponse);
    }

}
