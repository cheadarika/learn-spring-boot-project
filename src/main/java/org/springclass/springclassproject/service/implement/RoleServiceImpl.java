package org.springclass.springclassproject.service.implement;

import lombok.RequiredArgsConstructor;
import org.springclass.springclassproject.exception.ResourceNotFoundException;
import org.springclass.springclassproject.model.Role;
import org.springclass.springclassproject.repository.RoleRepository;
import org.springclass.springclassproject.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    @Override
    @Transactional
    public Role add(Role account) {
        return repository.save(account);
    }

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("404", "Role id %d not found".formatted(id)));
    }

    @Override
    public Role update(Long id, Role role) {
        final var entity = findById(id);
        entity.setName(role.getName());
        entity.setRole(role.getRole());
        return repository.save(entity);
    }
}
