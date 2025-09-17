package io.github.fokaBlog.controller;

import io.github.fokaBlog.dto.DtoMapper;
import io.github.fokaBlog.dto.PostDTO;
import io.github.fokaBlog.model.Post;
import io.github.fokaBlog.model.User;
import io.github.fokaBlog.services.PostService;
import io.github.fokaBlog.services.UserService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    // Criar Posts
    @PostMapping()
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO dto, @AuthenticationPrincipal User currentUser) {
        Post createdPost = postService.createPost(dto, currentUser);
        return ResponseEntity.ok(DtoMapper.toPostDTO(createdPost));
    }

//    // Listar todos os Posts
//    @GetMapping
//    public ResponseEntity<List<Post>> getAllPosts() {
//        return ResponseEntity.ok(postService.getAllPost());
//    }

    @GetMapping
    public List<PostDTO> getAllPosts() {
        return postService.getAllPost()
                .stream()
                .map(DtoMapper::toPostDTO)
                .toList();
    }

    // EndPoint com DTO para evitar Loopings em JSON
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id) {
        return postService.getPostById(id)
                .map(post -> ResponseEntity.ok(DtoMapper.toPostDTO(post)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar posts por titulo
    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchPosts(@RequestParam String title){
        return ResponseEntity.ok(postService.searchPostByTitle(title));
    }

    // Atualizar Post
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(
            @PathVariable Long id,
            @RequestBody PostDTO updatedPostDto
    ) {
        try {
            Post updated = postService.updatePost(id, updatedPostDto.getTitle(), updatedPostDto.getContent());
            return ResponseEntity.ok(DtoMapper.toPostDTO(updated));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar Posts
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    // Pegar o posts do autor autenticado
    @GetMapping("/my-posts")
    public List<PostDTO> getMyPosts(@AuthenticationPrincipal User currentUser) {
        List<Post> posts = postService.getPostsByAuthor(currentUser);

        return posts.stream()
                .map(DtoMapper::toPostDTO)
                .collect(Collectors.toList());
    }


}
