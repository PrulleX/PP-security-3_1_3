package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User showUserId(long id);
    void saveUser(User user);
    void updateUser(long id, User user);
    void deleteUser(long id);
}
