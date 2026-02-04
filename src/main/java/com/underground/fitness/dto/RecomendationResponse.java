package com.underground.fitness.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.underground.fitness.models.Activity;
import com.underground.fitness.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecomendationResponse {
        private String type;
        private String recomendation;
        private List<String> improvements;
        private List<String> suggestions;
        private List<String> saftey;

}
