package com.underground.fitness.service;

import com.underground.fitness.dto.ActivityRequest;
import com.underground.fitness.dto.ActivityResponse;
import com.underground.fitness.models.Activity;
import com.underground.fitness.models.User;
import com.underground.fitness.repository.ActivityRepository;
import com.underground.fitness.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ActivityService {


    private final ActivityRepository  activityRepository;
    private final UserRepository userRepository;

    private ActivityResponse convertActivityToActivityResponse(Activity activity) {
        return new ActivityResponse(
                activity.getId(),
                activity.getUser().getId(),
                activity.getType(),
                activity.getAdditionalMetrices(),
                activity.getCaloriesBurned(),
                activity.getDuration(),
                activity.getStartTime(),
                activity.getEndTime(),
                activity.getCreatedAt(),
                activity.getUpdatedAt()
        );
    }

    public List<ActivityResponse> getuserActivity(String userId) {
            List<Activity> list  = activityRepository.findByUserId(userId);
            List<ActivityResponse> responses = new ArrayList<>();
            for(Activity activity : list){
                ActivityResponse activityResponse = convertActivityToActivityResponse(activity);
                responses.add(activityResponse);
            }
            return responses;
    }

    public ActivityResponse trackActivity(ActivityRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Activity activity = Activity.builder()
                .user(user)
                .type(request.getType())
                .additionalMetrices(request.getAdditionalMetrices())
                .caloriesBurned(request.getCaloriesBurned())
                .duration(request.getDuration())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Activity saved = activityRepository.save(activity);

        return convertActivityToActivityResponse(saved);
    }
}
