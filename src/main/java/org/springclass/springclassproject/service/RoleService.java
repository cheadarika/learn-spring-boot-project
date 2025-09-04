package org.springclass.springclassproject.service;

import org.springclass.springclassproject.model.Role;

import java.util.List;

public interface RoleService {
    Role add(Role role);
    Role update(Long id, Role role);
    List<Role> findAll();
    Role findById(Long id);
}
