package com.zach.workout_tracker.service;

import com.zach.workout_tracker.dto.WorkoutRequest;
import com.zach.workout_tracker.dto.WorkoutResponse;
import com.zach.workout_tracker.dto.WorkoutSetRequest;
import com.zach.workout_tracker.entity.Exercise;
import com.zach.workout_tracker.entity.User;
import com.zach.workout_tracker.entity.Workout;
import com.zach.workout_tracker.entity.WorkoutSet;
import com.zach.workout_tracker.exception.ResourceNotFoundException;
import com.zach.workout_tracker.mapper.WorkoutMapper;
import com.zach.workout_tracker.repository.ExerciseRepository;
import com.zach.workout_tracker.repository.UserRepository;
import com.zach.workout_tracker.repository.WorkoutRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * Handles workout business logic for a user
 *
 * @author Zach Christensen
 */
@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutMapper workoutMapper;

    public WorkoutService(
            WorkoutRepository workoutRepository,
            UserRepository userRepository,
            ExerciseRepository exerciseRepository,
            WorkoutMapper workoutMapper) {
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
        this.workoutMapper = workoutMapper;
    }

    /**
     * Creates a workout with its sets for a user
     *
     * @param userId
     * @param request
     * @return
     */
    public WorkoutResponse create(Long userId, WorkoutRequest request) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));

        Workout workout = new Workout(user, request.workoutDate(), request.notes());

        for (WorkoutSetRequest setRequest : request.sets()) {
            Exercise exercise = exerciseRepository.findByIdAndUserId(setRequest.exerciseId(), userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Exercise not found: " + setRequest.exerciseId()));

            WorkoutSet set = new WorkoutSet(
                exercise,
                setRequest.setNumber(),
                setRequest.reps(),
                setRequest.weightKg()
            );
            workout.addSet(set);
        }

        Workout saved = workoutRepository.save(workout);
        return workoutMapper.toResponse(saved);
    }

    /**
     * Lists all workouts for a user
     *
     * @param userId
     * @return
     */
    public List<WorkoutResponse> listForUser(Long userId) {
        List<Workout> workouts = workoutRepository.findByUserId(userId);

        List<WorkoutResponse> responses = new ArrayList<>();
        for (Workout workout : workouts) {
            responses.add(workoutMapper.toResponse(workout));
        }

        return responses;
    }

    /**
     * Deletes a workout if it belongs to the user
     *
     * @param userId
     * @param workoutId
     */
    public void delete(Long userId, Long workoutId) {
        Workout workout = workoutRepository.findByIdAndUserId(workoutId, userId)
            .orElseThrow(() -> new ResourceNotFoundException("Workout not found: " + workoutId));

        workoutRepository.delete(workout);
    }
}
