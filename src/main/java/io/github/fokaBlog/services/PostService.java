package io.github.fokaBlog.services;

import io.github.fokaBlog.model.Post;
import io.github.fokaBlog.model.User;
import io.github.fokaBlog.repository.PostRepository;
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

    public Post createPost(Post post) {
        post.setCreatedAt(LocalDateTime.now());
        return postRepository.save(post);
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

    public List<Post> searchPostByTitle(String title) {
        return postRepository.findByTitleContainingIgnoreCase(title);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
