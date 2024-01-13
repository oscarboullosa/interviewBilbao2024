package org.example;

import org.example.app.model.User;
import org.example.app.repository.UserRepository;
import org.example.app.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetAllUsers() {
        // Arrange
        List<User> userList = Arrays.asList(new User(1L, "John"), new User(2L, "Jane"));
        when(userRepository.findAll()).thenReturn(userList);

        // Act
        List<User> result = userService.getAllUsers();

        // Assert
        assertEquals(userList, result);
    }

    @Test
    public void testGetUserById() {
        // Arrange
        Long userId = 1L;
        User user = new User(userId, "John");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.getUserById(userId);

        // Assert
        assertEquals(Optional.of(user), result);
    }

    @Test
    public void testCreateUser() {
        // Arrange
        User newUser = new User(null, "Alice");
        User savedUser = new User(1L, "Alice");
        when(userRepository.save(newUser)).thenReturn(savedUser);

        // Act
        User result = userService.createUser(newUser);

        // Assert
        assertEquals(savedUser, result);
    }

    @Test
    public void testUpdateUser() {
        // Arrange
        Long userId = 1L;
        User existingUser = new User(userId, "John");
        User updatedUser = new User(userId, "Updated John");
        when(userRepository.existsById(userId)).thenReturn(true);
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        // Act
        User result = userService.updateUser(userId, updatedUser);

        // Assert
        assertEquals(updatedUser, result);
    }

    @Test
    public void testUpdateUser_NotFound() {
        // Arrange
        Long userId = 1L;
        User updatedUser = new User(userId, "Updated John");
        when(userRepository.existsById(userId)).thenReturn(false);

        // Act
        User result = userService.updateUser(userId, updatedUser);

        // Assert
        assertNull(result);
    }

    @Test
    public void testDeleteUser() {
        // Arrange
        Long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(true);

        // Act
        userService.deleteUser(userId);

        // Assert
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void testDeleteUser_NotFound() {
        // Arrange
        Long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(false);

        // Act
        assertThrows(IllegalArgumentException.class, () -> userService.deleteUser(userId));
    }
}
