package edu.university.program.service.impl;

import edu.university.program.model.User;
import edu.university.program.repository.UserRepository;
import edu.university.program.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User oldUser = readById(user.getId());
        return userRepository.save(user);
    }

    @Override
    public User readById(long id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.get();
    }

    @Override
    public void delete(long id) {
        User user = readById(id);
        userRepository.delete(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }
}
