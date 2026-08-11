package com.zach.workout_tracker.dto;
import java.time.LocalDate;
import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record WorkoutRequest(
    @NotNull LocalDate workoutDate,
    String notes,
    @NotEmpty @Valid List<WorkoutSetRequest> sets
) {}