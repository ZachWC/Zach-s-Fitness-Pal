package com.zach.workout_tracker.mapper;
import com.zach.workout_tracker.dto.WorkoutResponse;
import com.zach.workout_tracker.dto.WorkoutSetResponse;
import com.zach.workout_tracker.entity.Workout;
import com.zach.workout_tracker.entity.WorkoutSet;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Converts workout entities into API response DTOs
 *
 * @author Zach Christensen
 */
@Component
public class WorkoutMapper {

    /**
     * Maps a single Workout to a WorkoutResponse
     * 
     * @param workout
     * @return
     */
    public WorkoutResponse toResponse(Workout workout) {
        List<WorkoutSetResponse> setResponses = new ArrayList<>();

        for (WorkoutSet set : workout.getSets()){ // Turn each set on this workout into a response
            setResponses.add(toSetResponse(set));
        }

        return new WorkoutResponse(
            workout.getId(),
            workout.getWorkoutDate(),
            workout.getNotes(),
            setResponses
        );
    }

    /**
     * Maps a single WorkoutSet to a WorkoutSetResponse
     * 
     * @param set
     * @return
     */
    public WorkoutSetResponse toSetResponse(WorkoutSet set) {
        return new WorkoutSetResponse(
            set.getId(),
            set.getExercise().getId(),
            set.getSetNumber(),
            set.getReps(),
            set.getWeightKg()
        );
    }
}
