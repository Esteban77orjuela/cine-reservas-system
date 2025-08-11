package com.cine.authservice.service.impl;

import com.cine.authservice.dto.RegisterRequest;
import com.cine.authservice.entity.User;
import com.cine.authservice.repository.UserRepository;
import com.cine.authservice.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail()))
            throw new IllegalArgumentException("Email already in use");

        String encodedPassword = passwordEncoder.encode(req.getPassword());

        User user = new User();
        user.setEmail(req.getEmail());
        user.setUsername(req.getUsername());
        user.setPassword(encodedPassword);
        user.setRole(User.Role.USER);
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);



    }
}
