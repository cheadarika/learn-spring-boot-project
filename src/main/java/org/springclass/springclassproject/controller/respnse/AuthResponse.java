package org.springclass.springclassproject.controller.respnse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    // Getters and Setters
    private String token;
    private String username;

    public AuthResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }
}
