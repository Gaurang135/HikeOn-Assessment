package com.assessment.hikeOn.Dto;

import com.assessment.hikeOn.Entities.Occupation;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewUserRequest {
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private LocalDate dateOfBirth;
    @NotNull
    private Occupation occupation;
}
