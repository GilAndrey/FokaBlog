package io.github.fokaBlog.controller;

import io.github.fokaBlog.dto.CommentDTO;
import io.github.fokaBlog.dto.CreateCommentDTO;
import io.github.fokaBlog.dto.DtoMapper;
import io.github.fokaBlog.model.Comment;
import io.github.fokaBlog.model.User;
import io.github.fokaBlog.services.CommentService;
import io.github.fokaBlog.services.PostService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }


//    @PostMapping("/post/{postId}")
//    public ResponseEntity<CommentDTO> addComment(@PathVariable Long postId, @RequestBody Comment comment) {
//        Comment saved = commentService.createComment(postId, comment);
//        return ResponseEntity.ok(DtoMapper.toCommentDTO(saved));
//    }



    // Procurar comentários de um post
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable Long postId) {
        return postService.getPostById(postId)
                .map(post -> ResponseEntity.ok(commentService.getCommentsByPost(post)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Atualizar comentario
    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long id, @RequestBody CommentDTO updateCommentDto) {
        try {
            Comment updated = commentService.updateComment(id, updateCommentDto.getContent());
            return ResponseEntity.ok(DtoMapper.toCommentDTO(updated));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar comentario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

}
