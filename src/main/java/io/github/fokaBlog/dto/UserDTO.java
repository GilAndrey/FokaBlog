package io.github.fokaBlog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {

        @Schema
        private Long id;
        @Schema(description = "display name")
        private String username;
        @Schema(description = "User email")
        private String email;
        @Schema(description = "Posts List")
        private List<PostDTO> posts;
}
