package com.example.demo.user;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }
    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable(value = "id") int userId) throws NotFoundException{
        return userService.getUser(userId);
    }

    @PostMapping
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }
    @PutMapping
    public void updateUser(@RequestBody User user) throws InvalidAttributeValueException, NotFoundException {
        userService.updateUser(user);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody User user) throws NotFoundException {
        userService.deleteUser(user.getUserId());
    }
}
