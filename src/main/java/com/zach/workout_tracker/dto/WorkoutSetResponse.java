package com.zach.workout_tracker.dto;
import java.math.BigDecimal;

public record WorkoutSetResponse(
    Long id,
    Long exerciseId,
    Integer setNumber,
    Integer reps,
    BigDecimal weightKg
) {}
