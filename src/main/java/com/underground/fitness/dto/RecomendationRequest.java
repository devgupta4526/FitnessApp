package com.underground.fitness.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.underground.fitness.models.Activity;
import com.underground.fitness.models.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecomendationRequest {
    private String type;
    private String userId;
    private String activityId;
    private String recomendation;
    private List<String> improvements;
    private List<String> suggestions;
    private List<String> saftey;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
