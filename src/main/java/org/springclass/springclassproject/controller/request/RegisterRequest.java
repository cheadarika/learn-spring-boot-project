package org.springclass.springclassproject.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springclass.springclassproject.model.Role;

import java.util.List;

@Getter
@Setter
@Value
public class RegisterRequest {
    String username;
    String password;
    String email;
    List<Role> roles;
}
