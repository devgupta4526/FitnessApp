package com.underground.fitness.controller;


import com.underground.fitness.dto.LoginRequest;
import com.underground.fitness.dto.LoginResponse;
import com.underground.fitness.dto.RegisterRequest;
import com.underground.fitness.dto.UserResponse;
import com.underground.fitness.models.User;
import com.underground.fitness.security.JwtUtils;
import com.underground.fitness.service.UserService;
import jakarta.validation.Valid;
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
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterRequest request){
        return ResponseEntity.ok(userService.createUser(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        try{
            User user = userService.authenticate(loginRequest);
            String token = jwtUtils.generateTokenFromUserId(user.getId(),user.getRole().name());
            return ResponseEntity.ok(new LoginResponse(
                    token,
                    userService.convertUserToUserResponse(user)
            ));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(401).build();
        }
    }


}
