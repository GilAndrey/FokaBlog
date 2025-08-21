package io.github.fokaBlog.repository;

import io.github.fokaBlog.model.Post;
import io.github.fokaBlog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByAuthor(User author);

    List<Post> findByTitleContainingIgnoreCase(String title);

}
