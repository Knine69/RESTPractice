package com.example.demo.userTest;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import com.example.demo.user.UserService;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Autowired
    MockMvc mockMvc;

    UserRepository userRepository = mock(UserRepository.class);
    UserService userService = new UserService(userRepository);

    public List<User> exampleUsers() {
        return List.of(new User("Maria Doe", "Whatever", "Mariadoe@gmail.com"),
                new User("Stephan Doe", "However", "phanodoe@gmail.com"));
    }

    public User exampleUser() {
        return new User("Maria Doe", "Whatever", "Mariadoe@gmail.com");
    }

    @Test
    public void getUsersTest() {
        List<User> userList = exampleUsers();
        when(userRepository.findAll()).thenReturn(userList);
        List<User> opposite = userService.getUsers();
        assertEquals(userList, opposite);
    }

    @Test
    void getUserTest() throws NotFoundException {
        User user = exampleUser();
        when(userRepository.findById(anyInt())).thenReturn(Optional.ofNullable(user));
        assertEquals(user, userService.getUser(0).get());
    }

    @Test
    void addUserTest() {
        User user = exampleUser();
        assertEquals(userService.addUser(user), user);
    }

    @Test
    void deleteUserTestFound() throws NotFoundException {
        when(userRepository.findById(1)).thenReturn(Optional.of(exampleUsers().get(1)));
        userService.deleteUser(1);
        verify(userRepository).deleteById(1);
    }

    @Test
    void deleteUserTestNotFound() throws NotFoundException {
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(null));
        userService.deleteUser(1);
    }

    @Test
    void updateUserFoundTest() throws NotFoundException, InvalidAttributeValueException {
        User user1 = exampleUsers().get(0);
        user1.setUserName("DontKnowBoo");
        user1.setUserId(1);
        when(userRepository.findById(user1.getUserId())).thenReturn(Optional.of(user1));
        userService.updateUser(user1);
        verify(userRepository).save(user1);
    }

    @Test
    void updateUserNotFoundTest() {
        assertThrows(NotFoundException.class, () -> {
            User user = exampleUsers().get(0);
            user.setUserId(1);
            when(userRepository.findById(anyInt())).thenReturn(Optional.ofNullable(null));
            userService.updateUser(user);

        });
    }

    @Test
    void updateUserInvalidID() {
        assertThrows(InvalidAttributeValueException.class, () -> {
            User user = exampleUsers().get(0);
            when(userRepository.findById(anyInt())).thenReturn(Optional.ofNullable(null));
            userService.updateUser(user);
        });
    }
}
