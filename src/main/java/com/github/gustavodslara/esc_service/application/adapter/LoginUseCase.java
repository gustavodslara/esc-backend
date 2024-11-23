package com.github.gustavodslara.esc_service.application.adapter;

import com.github.gustavodslara.esc_service.domain.entity.AuthRequest;
import com.github.gustavodslara.esc_service.domain.entity.AuthResponse;
import com.github.gustavodslara.esc_service.domain.entity.JwtUtil;
import com.github.gustavodslara.esc_service.domain.entity.User;
import com.github.gustavodslara.esc_service.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private
    PasswordEncoder passwordEncoder;

    public AuthResponse authenticate(AuthRequest authRequest) {
        User user = userRepository.findByUsername(authRequest.getUsername());

        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
          return null;
        }

        String token = jwtUtil.generateToken(user);

        return new AuthResponse(user, token);
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}