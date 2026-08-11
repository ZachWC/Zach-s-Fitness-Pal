package com.zach.workout_tracker.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record WorkoutSetRequest( 
    @NotNull Long exerciseId,
    @NotNull Integer setNumber,
    @NotNull Integer reps,
    BigDecimal weightKg
) {}
   


