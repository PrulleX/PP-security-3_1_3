package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;


import javax.transaction.Transactional;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User showUserId(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(long id, User user) {
        User updatedUser = userRepository.findById(id).orElse(null);

        if (updatedUser != null) {
            updatedUser.setEmail(user.getEmail());
            updatedUser.setUserName(user.getUserName());
            updatedUser.setSurName(user.getSurName());
            updatedUser.setAge(user.getAge());
            updatedUser.setRoles(user.getRoles());

            if (!updatedUser.getPassword().equals(user.getPassword())) {
                updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }
        }
    }

    @Override
    @Transactional
    @DeleteMapping("/users/id")
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
