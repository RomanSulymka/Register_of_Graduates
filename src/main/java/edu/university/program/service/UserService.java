package edu.university.program.service;

import edu.university.program.model.User;

import java.util.List;

public interface UserService {
    User create (User user);
    User update (User user);
    User readById (long id);
    void delete (long id);
    List<User> getAll();

}
