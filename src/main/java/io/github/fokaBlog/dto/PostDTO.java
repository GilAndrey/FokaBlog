package io.github.fokaBlog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostDTO {

    @Schema
    private Long id;
    @Schema(description = "Post Title")
    private String title;
    @Schema(description = "Post content")
    private String content;
    @Schema(description = "Post author")
    private String authUsername;

    @Schema
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Schema
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;

    @Schema
    private List<CommentDTO> comments;
}
