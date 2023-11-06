package com.interntask.bookstore.service;

import com.interntask.bookstore.entity.User;
import com.interntask.bookstore.exception.DuplicateKeyException;
import com.interntask.bookstore.model.request.AuthenticationRequest;
import com.interntask.bookstore.model.request.RegisterRequest;
import com.interntask.bookstore.model.response.AuthenticationResponse;
import com.interntask.bookstore.repository.UserRepository;
import com.interntask.bookstore.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateKeyException("Username already exists!");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateKeyException("Email already exists!");
        }

        User user = User.builder()
                .name(request.getFirstName())
                .surname(request.getLastName())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);

        return AuthenticationResponse.builder()
                .successMessage("User registered successfully!")
                .build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword())
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }
}
