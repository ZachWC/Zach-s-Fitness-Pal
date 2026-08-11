package com.zach.workout_tracker.dto;

import java.time.LocalDate;
import java.util.List;

public record WorkoutResponse(
    Long id,
    LocalDate workoutDate,
    String notes,
    List<WorkoutSetResponse> sets
) {}
