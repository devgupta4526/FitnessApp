package com.underground.fitness.dto;


import com.underground.fitness.models.enums.UserRole;
import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private UserRole role;
}
