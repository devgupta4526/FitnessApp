package com.underground.fitness.dto;


import com.underground.fitness.models.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Email is Required")
    @Email(message = "Invalid Email")
    private String email;
    @NotBlank(message = "Password is Required")
    @Size(min = 6, max = 12,message = "Password Length should be 5<pass<13")
    private String password;
    private String firstName;
    private String lastName;
    private UserRole role;
}
