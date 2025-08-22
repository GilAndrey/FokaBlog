package io.github.fokaBlog.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDTO {

    private Long id;
    private String content;
    private String authUsername;
    private LocalDateTime createdAt;

}
