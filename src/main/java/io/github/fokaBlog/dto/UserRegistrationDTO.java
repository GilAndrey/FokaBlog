package io.github.fokaBlog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDTO {

    @Schema(description = "User name to view", example = "gil_andrey")
    private String username;

    @Schema(description = "Email to Login", example = "gil_andrey@example.com")
    private String email;

    @Schema(description = "Password minimum of 8 characters", example = "password123")
    private String password;
}
