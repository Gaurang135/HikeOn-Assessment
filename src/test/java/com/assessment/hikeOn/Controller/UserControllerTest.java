package com.assessment.hikeOn.Controller;

import com.assessment.hikeOn.Dto.NewUserRequest;
import com.assessment.hikeOn.Entities.Occupation;
import com.assessment.hikeOn.Exceptions.DuplicateUserException;
import com.assessment.hikeOn.Exceptions.RegisteredMailException;
import com.assessment.hikeOn.Exceptions.UserUnderageException;
import com.assessment.hikeOn.Service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private IUserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_shouldReturnOkResponse_whenUserIsRegisteredSuccessfully() {
        // Arrange
        NewUserRequest newUserRequest = new NewUserRequest("Yash", "Yash@example.com", LocalDate.of(2001,5,30), Occupation.CHEF);
//        when(userService.registerUser(newUserRequest)).thenReturn();

        // Act
        ResponseEntity<String> response = userController.registerUser(newUserRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User Registered Successfully", response.getBody());
    }

    @Test
    void registerUser_shouldReturnBadRequestResponse_whenUserUnderageExceptionIsThrown() {
        // Arrange
        NewUserRequest newUserRequest = new NewUserRequest("Yash", "Yash@example.com", LocalDate.of(2018,5,30), Occupation.CHEF);
        doThrow(new UserUnderageException("The minimum age should be 18 years.")).when(userService).registerUser(newUserRequest);

        // Act
        ResponseEntity<String> response = userController.registerUser(newUserRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("The minimum age should be 18 years."));
    }

    @Test
    void registerUser_shouldReturnBadRequestResponse_whenRegisteredMailExceptionIsThrown() {
        // Arrange
        NewUserRequest newUserRequest = new NewUserRequest("Gaurang", "yash@example.com", LocalDate.of(2001,5,30), Occupation.DEVELOPER);
        doThrow(new RegisteredMailException("The mail provided is already linked with an existing account.")).when(userService).registerUser(newUserRequest);

        // Act
        ResponseEntity<String> response = userController.registerUser(newUserRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("The mail provided is already linked with an existing account."));
    }

    @Test
    void registerUser_shouldReturnBadRequestResponse_whenDuplicateUserExceptionIsThrown() {
        // Arrange
        NewUserRequest newUserRequest = new NewUserRequest("Yash", "Yash@example.com", LocalDate.of(2001,5,30), Occupation.CHEF);
        doThrow(new DuplicateUserException("The user is already registered in the system.")).when(userService).registerUser(newUserRequest);

        // Act
        ResponseEntity<String> response = userController.registerUser(newUserRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("The user is already registered in the system."));
    }
}
