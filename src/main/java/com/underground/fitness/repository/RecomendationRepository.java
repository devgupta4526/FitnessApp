package com.underground.fitness.repository;

import com.underground.fitness.models.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecomendationRepository extends JpaRepository<Recommendation,String> {
    List<Recommendation> findByUser_Id(String userId);

    List<Recommendation> findByActivity_Id(String activityId);
}
