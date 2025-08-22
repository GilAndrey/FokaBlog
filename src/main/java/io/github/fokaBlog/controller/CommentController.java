package io.github.fokaBlog.controller;

import io.github.fokaBlog.dto.CommentDTO;
import io.github.fokaBlog.dto.DtoMapper;
import io.github.fokaBlog.model.Comment;
import io.github.fokaBlog.services.CommentService;
import io.github.fokaBlog.services.PostService;
import org.springframework.http.ResponseEntity;
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

    /*
    * Erro na criação de comentarios!
    * Estao sendo direcionados para o primeiro User
    * Estão com os dados do author em Null
    * */


//    @PostMapping("/post/{postId}")
//    public ResponseEntity<Comment> createComment(@PathVariable Long postId, @RequestBody Comment comment) {
//        return postService.getPostById(postId)
//                .map(post -> {
//                    comment.setPost(post);
//                    return ResponseEntity.ok(commentService.createComment(comment));
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }

    @PostMapping("/post/{postId}")
    public ResponseEntity<CommentDTO> addComment(@PathVariable Long postId, @RequestBody Comment comment) {
        Comment saved = commentService.createComment(postId, comment);
        return ResponseEntity.ok(DtoMapper.toCommentDTO(saved));
    }



    // Procurar comentários de um post
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable Long postId) {
        return postService.getPostById(postId)
                .map(post -> ResponseEntity.ok(commentService.getCommentsByPost(post)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar comentario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

}
