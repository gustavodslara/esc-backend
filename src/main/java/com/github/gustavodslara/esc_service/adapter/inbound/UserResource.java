package com.github.gustavodslara.esc_service.adapter.inbound;

import com.github.gustavodslara.esc_service.application.adapter.LoginUseCase;
import com.github.gustavodslara.esc_service.domain.entity.AuthRequest;
import com.github.gustavodslara.esc_service.domain.entity.AuthResponse;
import com.github.gustavodslara.esc_service.domain.entity.User;
import com.github.gustavodslara.esc_service.domain.entity.UserAuthority;
import com.github.gustavodslara.esc_service.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LoginUseCase loginUseCase;

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user, @RequestParam(defaultValue = "false") boolean isAdmin) {
        user.setAuthority(isAdmin ? UserAuthority.ROLE_ADMIN : UserAuthority.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/entrar")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {

        try {
            var user = loginUseCase.authenticate(authRequest);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/users/{id}/change-password")
    public ResponseEntity<Map<String, String>> changePassword(
            @PathVariable Long id,
            @RequestBody Map<String, String> passwords) {

        String oldPassword = passwords.get("oldPassword");
        String newPassword = passwords.get("newPassword");

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Incorrect old password"));
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return ResponseEntity.ok(Map.of("message", "Password changed successfully"));
    }
}