package com.assessment.hikeOn.RepositoryService;

import com.assessment.hikeOn.Entities.User;
import com.assessment.hikeOn.Exceptions.DuplicateUserException;
import com.assessment.hikeOn.Exceptions.RegisteredMailException;
import com.assessment.hikeOn.Repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserRepositoryServiceTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserRepositoryService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_shouldSaveUser() {
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.getUserByEmail("test@example.com")).thenReturn(Optional.empty());
        when(userRepository.findAll()).thenReturn(new ArrayList<>());

        assertDoesNotThrow(() -> userService.registerUser(user));

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void registerUser_shouldThrowRegisteredMailException_whenEmailIsAlreadyRegistered() {
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.getUserByEmail("test@example.com")).thenReturn(Optional.of(user));

        assertThrows(RegisteredMailException.class, () -> userService.registerUser(user));

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void registerUser_shouldThrowDuplicateUserException_whenUserIsAlreadyRegistered() {
        User user = new User();
        user.setEmail("test@example.com");

        List<User> userList = new ArrayList<>();
        userList.add(user);

        when(userRepository.getUserByEmail("test@example.com")).thenReturn(Optional.empty());
        when(userRepository.findAll()).thenReturn(userList);

        assertThrows(DuplicateUserException.class, () -> userService.registerUser(user));

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void getID() {
        // You may need to mock UserRepository as well if it is used in this method
        // Mocking userRepository is not shown here since it's just a placeholder method
        when(userRepository.findAll()).thenReturn(new ArrayList<>());

        int id = userService.getID();

        assertEquals(1, id);
    }

    @Test
    void validateRequest_shouldThrowRegisteredMailException_whenEmailIsAlreadyRegistered() {
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.getUserByEmail("test@example.com")).thenReturn(Optional.of(user));

        assertThrows(RegisteredMailException.class, () -> userService.validateRequest(user));
    }

    @Test
    void validateRequest_shouldThrowDuplicateUserException_whenUserIsAlreadyRegistered() {
        User user = new User();
        user.setEmail("test@example.com");

        List<User> userList = new ArrayList<>();
        userList.add(user);

        when(userRepository.getUserByEmail("test@example.com")).thenReturn(Optional.empty());
        when(userRepository.findAll()).thenReturn(userList);

        assertThrows(DuplicateUserException.class, () -> userService.validateRequest(user));
    }
}
