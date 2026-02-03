package com.underground.fitness.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.underground.fitness.models.User;
import com.underground.fitness.models.enums.ActivityType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ActivityRequest {
    private String id;
    private String userId;
    private ActivityType type;
    private Map<String,Object> additionalMetrices;
    private Integer caloriesBurned;
    private Integer duration;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
