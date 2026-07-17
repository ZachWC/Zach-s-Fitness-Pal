package com.zach.workout_tracker.repository;

import com.zach.workout_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<User, Long> {
}
