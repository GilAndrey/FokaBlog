package io.github.fokaBlog.services;

import io.github.fokaBlog.model.User;
import io.github.fokaBlog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // User create
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Encontrar todos os Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // buscar por id
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
