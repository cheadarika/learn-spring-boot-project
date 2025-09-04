package org.springclass.springclassproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springclass.springclassproject.controller.request.LoginRequest;
import org.springclass.springclassproject.controller.request.RegisterRequest;
import org.springclass.springclassproject.controller.respnse.AuthResponse;
import org.springclass.springclassproject.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
//        try {
//            AuthResponse response = authService.login(loginRequest);
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
//        try {
//            AuthResponse response = authService.register(registerRequest);
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            log.info("=====>>> Response: {}", e.getMessage());
//            return ResponseEntity.badRequest().build();
//        }
    }
}
