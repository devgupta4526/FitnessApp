package com.underground.fitness.service;

import com.underground.fitness.dto.LoginRequest;
import com.underground.fitness.dto.RegisterRequest;
import com.underground.fitness.dto.UserResponse;
import com.underground.fitness.models.User;
import com.underground.fitness.models.enums.UserRole;
import com.underground.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserResponse createUser(RegisterRequest request) {
        UserRole userRole = request.getRole() != null ? request.getRole() :  UserRole.USER;
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(userRole)
                .build();
        userRepository.save(user);

        return convertUserToUserResponse(user);
    }

    public UserResponse convertUserToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public User authenticate(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if(user == null){
            throw new UsernameNotFoundException("Invalid email or password");
        }

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new BadCredentialsException("Invalid credentials");
        }
        return user;
    }
}
