package com.zach.workout_tracker.repository;

import com.zach.workout_tracker.entity.Exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findByUserId(Long userId);
    Optional<Exercise> findByIdAndUserId(Long id, Long userId);
}

