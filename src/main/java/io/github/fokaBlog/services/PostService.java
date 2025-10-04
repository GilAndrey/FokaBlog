package io.github.fokaBlog.services;

import io.github.fokaBlog.dto.PostDTO;
import io.github.fokaBlog.model.Post;
import io.github.fokaBlog.model.User;
import io.github.fokaBlog.repository.PostRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(PostDTO postDto, User author) {
        Post newPost = new Post();
        newPost.setTitle(postDto.getTitle());
        newPost.setContent(postDto.getContent());
        newPost.setAuthor(author);
        newPost.setCreatedAt(LocalDateTime.now());
        return postRepository.save(newPost);
    }

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> getPostsByAuthor(User author) {
        return postRepository.findByAuthor(author);
    }

    // Implementando verificação de permissão para atualizar post
    public Post updatePost(Long id, PostDTO postDto, User currentUser) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found!!"));

        checkOwnershipOrAdmin(post, currentUser);

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setUpdatedAt(LocalDateTime.now());

        return postRepository.save(post);
    }

    public List<Post> searchPostByTitle(String title) {
        return postRepository.findByTitleContainingIgnoreCase(title);
    }

    // Implementando verificação de permissão para deletar post
    public void deletePost(Long id, User currentUser) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found!!"));

        checkOwnershipOrAdmin(post, currentUser);

        postRepository.deleteById(id);
    }

    /**
     *  Verifica se o usuário atual é o autor do post ou um administrador.
     *  @throws AccessDeniedException se o usuário não tiver permissão.
     */
    private void checkOwnershipOrAdmin(Post post, User currentUser) {
        boolean isAdmin = currentUser.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        if (!post.getAuthor().getId().equals(currentUser.getId()) && !isAdmin) {
            throw new AccessDeniedException("You do not have permission to modify this post.");
        }
    }
}
