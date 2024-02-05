package it.academy.controller.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateStudentDto {
    @NotBlank(message = "First name must be not empty")
    private String firstName;

    @NotBlank(message = "Last name must be not empty")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
