package com.example.demo.user;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User findUser(User user) {
        return userRepository.findByUserEmail(user.getUserEmail());
    }

    public Optional<User> getUser(int userId) throws NotFoundException {
        return userRepository.findById(userId);
    }

    public void addUser(User user) {
        User theUser = findUser(user);
        if (theUser != null) {
            throw new IllegalStateException("Student already exists!");
        }
        System.out.println("Add User effective.");
        userRepository.save(user);
    }

    public void updateUser(User user) throws NotFoundException {
        if (userRepository.getById(user.getUserId()) != null) {
            System.out.println("Update User effective.");
            userRepository.save(user);
        }
    }

    public void deleteUser(int userId) throws NotFoundException {
        System.out.println("Delete user effective.");
        userRepository.deleteById(userId);
    }
}
