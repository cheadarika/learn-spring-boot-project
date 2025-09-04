package org.springclass.springclassproject.controller.respnse;

import lombok.Value;
import org.springclass.springclassproject.model.Role;
import org.springclass.springclassproject.model.User;

import java.util.List;

@Value
public class UserResponse {
    Long id;
    String username;
    String email;
    List<Role> roles;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }
}
