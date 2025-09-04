package org.springclass.springclassproject.controller.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    // Getters and Setters
    private String usernameOrEmail;
    private String password;

    // Constructors
    public LoginRequest() {
    }

    public LoginRequest(String usernameOrEmail, String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }

}
