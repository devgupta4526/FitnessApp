package com.underground.fitness.controller;


import com.underground.fitness.dto.RecomendationRequest;
import com.underground.fitness.dto.RecomendationResponse;
import com.underground.fitness.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recommendation")
public class RecommendationController {

    private final RecommendationService recomendationService;
    private RecommendationService recommendationService;

    public RecommendationController(RecommendationService recomendationService) {
        this.recomendationService = recomendationService;
    }

    @PostMapping("/generate")
    public ResponseEntity<RecomendationResponse> generateRecomendation(
            @RequestBody RecomendationRequest recommendationRequest
    ){
        RecomendationResponse recommendationResponse = recomendationService.generateRecommendation(recommendationRequest);
        return ResponseEntity.ok(recommendationResponse);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RecomendationResponse>> getRecommendations(
        @PathVariable String userId
    ){
           List<RecomendationResponse> list =  recomendationService.getUserRecommendation(userId);
           return ResponseEntity.ok(list);
    }

}
