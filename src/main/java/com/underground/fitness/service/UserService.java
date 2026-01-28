package com.underground.fitness.service;

import com.underground.fitness.dto.RegisterRequest;
import com.underground.fitness.dto.UserResponse;
import com.underground.fitness.models.User;
import com.underground.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public UserResponse createUser(RegisterRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
        userRepository.save(user);

        return convertUserToUserResponse(user);
    }

    private UserResponse convertUserToUserResponse(User user) {
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

}
