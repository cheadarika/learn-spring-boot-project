package org.springclass.springclassproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springclass.springclassproject.Utils.JwtUtil;
import org.springclass.springclassproject.controller.request.LoginRequest;
import org.springclass.springclassproject.controller.request.RegisterRequest;
import org.springclass.springclassproject.controller.respnse.AuthResponse;
import org.springclass.springclassproject.exception.ResourceNotFoundException;
import org.springclass.springclassproject.model.Role;
import org.springclass.springclassproject.model.User;
import org.springclass.springclassproject.repository.UserRepository;
import org.springclass.springclassproject.service.implement.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public AuthResponse login(LoginRequest loginRequest) {
        log.info("=====>>> Login Request: {}", loginRequest.getUsernameOrEmail());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsernameOrEmail());

        if (!passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            throw new ResourceNotFoundException("403", "Incorrect password!!!");
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsernameOrEmail(), loginRequest.getPassword()
            ));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid credentials", e);
        }

        final String token = jwtUtil.generateToken(userDetails);
        return new AuthResponse(token, userDetails.getUsername());
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        log.info("=====>>> Register Request: {}", registerRequest);
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new ResourceNotFoundException("404", "Username already exists!");
        }
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new ResourceNotFoundException("404", "Email already exists!");
        }
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.addAllRole(registerRequest.getRoles());
        userRepository.save(user);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        final String token = jwtUtil.generateToken(userDetails);
        return new AuthResponse(token, user.getUsername());
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("404", "Account id %d not found".formatted(id)));
    }
}
