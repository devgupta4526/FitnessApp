package com.underground.fitness.controller;


import com.underground.fitness.models.User;
import com.underground.fitness.repository.UserRepository;
import com.underground.fitness.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/user/register")
    private User registerUser(@RequestBody User user){
        return userService.createUser(user);
    }


}
