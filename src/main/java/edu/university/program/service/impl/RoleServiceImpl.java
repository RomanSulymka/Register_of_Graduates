package edu.university.program.service.impl;

import edu.university.program.exception.NullEntityReferenceException;
import edu.university.program.model.Role;
import edu.university.program.repository.RoleRepository;
import edu.university.program.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role create(Role role) {
        try {
            return roleRepository.save(role);
        }catch (IllegalArgumentException e){
            throw new NullEntityReferenceException("role can`t be 'null'");
        }
    }

    @Override
    public Role update(Role role) {
        if (role != null){
            Role oldRole = readById(role.getId());
            if (oldRole != null){
                return roleRepository.save(role);
            }
        }
        throw new NullEntityReferenceException("role can`t be 'null'");
    }

    @Override
    public Role readById(long id) {
        Optional<Role> optional = roleRepository.findById(id);
        return optional.get();
    }

    @Override
    public void delete(long id) {
        Role role = readById(id);
        if (role != null){
            roleRepository.delete(role);
        }else{
            throw new NullEntityReferenceException("Role cannot be 'null'");
        }
    }

    @Override
    public List<Role> getAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.isEmpty() ? new ArrayList<>() : roles;
    }
}
