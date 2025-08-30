package io.github.fokaBlog.dto;

import io.github.fokaBlog.model.Comment;
import io.github.fokaBlog.model.Post;
import io.github.fokaBlog.model.User;

import java.util.stream.Collectors;

public class DtoMapper {

    public static UserDTO toUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());

        if (user.getPosts() != null) {
            dto.setPosts(
                    user.getPosts().stream()
                            .map(DtoMapper::toPostDTO)
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }

    public static PostDTO toPostDTO(Post post) {
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setAuthUsername(post.getAuthor().getUsername());
        dto.setCreatedAt(post.getCreatedAt());

        if (post.getComments() != null) {
            dto.setComments(
                    post.getComments().stream()
                            .map(DtoMapper::toCommentDTO)
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }

    public static CommentDTO toCommentDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setAuthUsername(comment.getAuthor().getUsername());
        dto.setCreatedAt(comment.getCreatedAt());
        return dto;
    }
}
