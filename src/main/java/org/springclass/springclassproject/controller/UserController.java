package org.springclass.springclassproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springclass.springclassproject.controller.respnse.AuthResponse;
import org.springclass.springclassproject.controller.respnse.UserResponse;
import org.springclass.springclassproject.model.Role;
import org.springclass.springclassproject.model.User;
import org.springclass.springclassproject.service.AuthService;
import org.springclass.springclassproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private AuthService service;

    @Autowired
    private RoleService roleService;

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new UserResponse(service.getUserById(id)));
    }

    @PostMapping("/add-role")
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.add(role));
    }
}
