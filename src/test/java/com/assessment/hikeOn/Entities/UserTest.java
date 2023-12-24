package com.assessment.hikeOn.Entities;

import com.assessment.hikeOn.Exceptions.UserUnderageException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    @Test
    void validateAge_shouldNotThrowException_whenUserIs18OrAbove() {
        User user = new User("Yash", "yash@example.com", LocalDate.of(2001,5,30), Occupation.DEVELOPER, CustomerGroup.DEVELOPER);

        // The user is 22 years old, so the validation should pass without throwing an exception
        assertDoesNotThrow(() -> user.validateAge(user.getDateOfBirth()));
    }

    @Test
    void validateAge_shouldThrowUserUnderageException_whenUserIsBelow18() {


        // The user is 16 years old, so the validation should throw a UserUnderageException
        assertThrows(UserUnderageException.class, () -> new User("Yash", "yash@example.com", LocalDate.of(2007,5,30), Occupation.CHEF, CustomerGroup.CHEF));
    }
}
