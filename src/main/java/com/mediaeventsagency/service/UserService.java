package com.mediaeventsagency.service;

import com.mediaeventsagency.model.Role;
import com.mediaeventsagency.model.User;
import com.mediaeventsagency.repository.UserRepository;
import com.mediaeventsagency.util.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Role getRoleByToken(String token) {
        String username = jwtUtil.getUserNameFromJwtToken(token);
        return userRepository.getRoleByEmail(username).get(0);
    }

    public User saveUserSignUp(String email, Set<Role> roles, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(roles);
        return userRepository.save(user);
    }
}
