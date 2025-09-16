package io.github.fokaBlog.controller;

import io.github.fokaBlog.dto.UserRegistrationDTO;
import io.github.fokaBlog.model.User;
import io.github.fokaBlog.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDTO dto) {
        User newUser = new User();
        newUser.setUsername(dto.getUsername());
        newUser.setEmail(dto.getEmail());
        newUser.setPassword(dto.getPassword());

        userService.createUser(newUser);

        return ResponseEntity.ok("User registered successfully!");
    }

    @Operation(
            summary = "Authenticates User and creates a session.",
            description = "Endpoint for login, body type is 'application/x-www-form-urlencoded' " +
                    "with 'username' keys (to email) and 'password'.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Login successful"),
                    @ApiResponse(responseCode = "401", description = "Authentication Failure")
            }
    )
    @PostMapping(value = "/login", consumes = "application/x-www-form-urlencoded")
    public void login(
            @Parameter(description = "User email.", required = true, schema = @Schema(type = "string", format = "email"))
            String username,
            @Parameter(description = "User password.", required = true, schema = @Schema(type = "string", format = "password"))
            String password
    ) {
        throw new IllegalStateException("This method should not be called, Managed by SpringSecurity. ");
    }
}
