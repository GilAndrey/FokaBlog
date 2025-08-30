package io.github.fokaBlog.services;

import io.github.fokaBlog.model.Comment;
import io.github.fokaBlog.model.Post;
import io.github.fokaBlog.model.User;
import io.github.fokaBlog.repository.CommentRepository;
import io.github.fokaBlog.repository.PostRepository;
import io.github.fokaBlog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {


    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    // Adicionando para Atualização de comentario
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Comment createComment(Long postId, Comment comment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found!"));

        comment.setPost(post);

        if (comment.getAuthor() != null && comment.getAuthor().getId() != null) {
            User author = userRepository.findById(comment.getAuthor().getId())
                    .orElseThrow(() -> new RuntimeException("Author not found!!"));
            comment.setAuthor(author);
        }

        comment.setCreatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    // Fluxo de Update, para o @PreUpdate rodar automaticamente
    public Comment updateComment(Long id, String newContent) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found!"));

        comment.setContent(newContent);
        return commentRepository.save(comment);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public List<Comment> getCommentsByPost(Post post) {
        return commentRepository.findByPost(post);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

}
