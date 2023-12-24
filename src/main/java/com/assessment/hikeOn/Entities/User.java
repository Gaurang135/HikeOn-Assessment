package com.assessment.hikeOn.Entities;

import com.assessment.hikeOn.Exceptions.UserUnderageException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("Users")
public class User {
    @Id
    private Integer id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    private Occupation occupation;
    private CustomerGroup customerGroup;

    public User(String name, String email, LocalDate dateOfBirth, Occupation occupation, CustomerGroup customerGroup) {
        validateAge(dateOfBirth);
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.occupation = occupation;
        this.customerGroup = customerGroup;
    }
    public void validateAge(LocalDate date) {

        if(ChronoUnit.YEARS.between(date,LocalDate.now()) < 18) {
            throw new UserUnderageException("The minimum age should be 18 years.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getDateOfBirth(), user.getDateOfBirth()) && getOccupation() == user.getOccupation() && getCustomerGroup() == user.getCustomerGroup();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateOfBirth(), getOccupation(), getCustomerGroup());
    }
}
