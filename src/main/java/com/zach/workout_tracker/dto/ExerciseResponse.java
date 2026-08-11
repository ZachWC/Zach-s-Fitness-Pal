package com.zach.workout_tracker.dto;

public record ExerciseResponse (
    Long id,
    String exerciseName,
    String muscleGroup
) {}
