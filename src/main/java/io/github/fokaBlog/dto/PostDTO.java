package io.github.fokaBlog.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostDTO {

    private Long id;
    private String title;
    private String content;
    private String authUsername;
    private LocalDateTime createdAt;

    private List<CommentDTO> comments;
}
