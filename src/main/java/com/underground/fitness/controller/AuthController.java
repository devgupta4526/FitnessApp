package com.underground.fitness.controller;


import com.underground.fitness.dto.RegisterRequest;
import com.underground.fitness.dto.UserResponse;
import com.underground.fitness.models.User;
import com.underground.fitness.repository.UserRepository;
import com.underground.fitness.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(userService.createUser(request));
    }


}
