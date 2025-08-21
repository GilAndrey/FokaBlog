package io.github.fokaBlog.controller;

import io.github.fokaBlog.model.Post;
import io.github.fokaBlog.services.PostService;
import io.github.fokaBlog.services.UserService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Post> createPost(@PathVariable Long userId, @RequestBody Post post) {
        return userService.getUserById(userId)
                .map(user -> {
                    post.setAuthor(user);
                    return ResponseEntity.ok(postService.createPost(post));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Listar todos os Posts
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPost());
    }

    // Buscar posts por ID
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar posts por titulo
    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchPosts(@RequestParam String title){
        return ResponseEntity.ok(postService.searchPostByTitle(title));
    }

    // Deletar Posts
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }


}
