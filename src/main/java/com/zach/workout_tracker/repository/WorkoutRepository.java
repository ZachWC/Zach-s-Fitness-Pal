package com.zach.workout_tracker.repository;

import com.zach.workout_tracker.entity.Workout;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    List<Workout> findByUserId(Long userId);
    Optional<Workout> findByIdAndUserId(Long id, Long userId);
}
