package edu.university.program.service;

import edu.university.program.model.Role;

import java.util.List;

public interface RoleService {
    Role create (Role role);
    Role update (Role role);
    Role readById (long id);
    void delete (long id);
    List<Role> getAll();
}
