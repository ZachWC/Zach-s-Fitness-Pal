package com.zach.workout_tracker.service;

import com.zach.workout_tracker.dto.ExerciseRequest;
import com.zach.workout_tracker.dto.ExerciseResponse;
import com.zach.workout_tracker.entity.Exercise;
import com.zach.workout_tracker.entity.User;
import com.zach.workout_tracker.exception.ResourceNotFoundException;
import com.zach.workout_tracker.repository.ExerciseRepository;
import com.zach.workout_tracker.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;

    public ExerciseService(ExerciseRepository exerciseRepository,
        UserRepository userRepository) {
        this.exerciseRepository = exerciseRepository;
        this.userRepository = userRepository;
    }

    public ExerciseResponse create(Long userId, ExerciseRequest request) {
        User user = userRepository.findById(userId) 
            .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));

        Exercise exercise = new Exercise(user, request.exerciseName(), request.muscleGroup());
        Exercise saved = exerciseRepository.save(exercise);
        
        return new ExerciseResponse(
            saved.getId(),
            saved.getExerciseName(),
            saved.getMuscleGroup()
        );

    }

    public List<ExerciseResponse> listForUser(Long userId) {
        List<Exercise> userList;
        userList = exerciseRepository.findByUserId(userId);

        List<ExerciseResponse> responses = new ArrayList<>();
        for (Exercise e: userList){ // convert Exercise into ExerciseResponse
            responses.add(new ExerciseResponse(e.getId(), e.getExerciseName(), e.getMuscleGroup()));
        }

        return responses;
    }

    public void delete(Long userId, Long exerciseId){
        Exercise exercise = exerciseRepository.findByIdAndUserId(exerciseId, userId)
            .orElseThrow(() -> new ResourceNotFoundException("Exercise not found: " + exerciseId));

        exerciseRepository.delete(exercise);
    }

}
