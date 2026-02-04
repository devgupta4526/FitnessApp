package com.underground.fitness.service;


import com.underground.fitness.dto.RecomendationRequest;
import com.underground.fitness.dto.RecomendationResponse;
import com.underground.fitness.models.Activity;
import com.underground.fitness.models.Recommendation;
import com.underground.fitness.models.User;
import com.underground.fitness.repository.ActivityRepository;
import com.underground.fitness.repository.RecomendationRepository;
import com.underground.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecomendationRepository recomendationRepository;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public RecomendationResponse generateRecommendation(RecomendationRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow();
        Activity activity = activityRepository.findById(request.getActivityId()).orElseThrow();

        Recommendation recommendation = Recommendation.builder()
                .type(request.getType())
                .user(user)
                .recomendation(request.getRecomendation())
                .saftey(request.getSaftey())
                .improvements(request.getImprovements())
                .activity(activity)
                .suggestions(request.getSuggestions())
                .build();
        recomendationRepository.save(recommendation);
        return convertRecomendationRequestToRecomendationResponse(recommendation);
    }


    private RecomendationResponse convertRecomendationRequestToRecomendationResponse(Recommendation request) {
          return new  RecomendationResponse(
        request.getType(),
        request.getRecomendation(),
        request.getImprovements(),
        request.getSuggestions(), request.getSaftey()
          );
    }


    public List<RecomendationResponse> getUserRecommendation(String userId) {
        List<Recommendation> recomendationList = recomendationRepository.findByUser_Id((userId));
        List<RecomendationResponse> recomendationResponseList = new ArrayList<>();

        for (Recommendation recommendation : recomendationList) {
            RecomendationResponse response = new RecomendationResponse(
                    recommendation.getType(),
                    recommendation.getRecomendation(),
                    recommendation.getImprovements(),
                    recommendation.getSuggestions(),
                    recommendation.getSaftey()
            );
            recomendationResponseList.add(response);
        }
        return recomendationResponseList;
    }

}
