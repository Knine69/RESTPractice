package com.example.demo.user;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;
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


    public Optional<User> getUser(int userId) throws NotFoundException {
        return userRepository.findById(userId);
    }

    public void addUser(User user) {
        userRepository.save(user);
        System.out.println("Add User effective.");
    }

    public void updateUser(User user) throws InvalidAttributeValueException, NotFoundException {
        if (user.getUserId() == 0) {
            throw new InvalidAttributeValueException();
        } else if (user.getUserId() > 0) {
            Optional<User> validate = userRepository.findById(user.getUserId());
            if (validate.isPresent()) {
                userRepository.save(user);
                System.out.println("Update User effective.");
            } else {
                throw new NotFoundException("Register not found.");
            }

        }
    }

    public void deleteUser(int userId) throws NotFoundException {
        userRepository.deleteById(userId);
        System.out.println("Delete user effective.");
    }
}
