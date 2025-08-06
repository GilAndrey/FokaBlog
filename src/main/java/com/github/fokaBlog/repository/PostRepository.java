package com.github.fokaBlog.repository;

import com.github.fokaBlog.model.Post;
import com.github.fokaBlog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(User user);

}
