package com.github.fokaBlog.security;

import com.github.fokaBlog.model.User;
import com.github.fokaBlog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    /*
    *  Method automatically called by Spring Security when attempting to authenticate.
     * Searches for the user by email and adapts it with CustomUserDetails
    * */
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        return new CustomUserDetails(user);
    }
}
