package ledge.muscleup.business;

import java.util.List;

import ledge.muscleup.model.exercise.WorkoutExerciseDuration;
import ledge.muscleup.model.workout.Workout;

/**
 * An Interface for communicating with the database to retrieve, add, and remove workouts from the
 * database, and add exercises to workouts in the database
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-07
 */
public interface InterfaceAccessWorkouts {

    /**
     * This method gets a workout from the database with the given name
     * @param workoutName the name of the workout
     * @return a workout from the database with the given name, if it exists. Otherwise, returns null
     */
    Workout getWorkout(String workoutName);

    /**
     * This method gets a list of workouts in the database
     * @return a list of the workouts stored in the database
     */
    List<Workout> getWorkoutsList();

    /**
     * This method gets the names of all workouts in the database in the form of a list
     * @return a list of workout names in the database
     */
    List<String> getWorkoutNamesList();

    /**
     * Adds a new workout to the database
     * @param workout the workout to be added to the database
     */
    void insertWorkout(Workout workout);

    /**
     * Removes a workout from the database, if it exists
     * @param workout the workout to be removed
     */
    public void removeWorkout(Workout workout);

    /**
     * Adds an exercise stored in the database to a workout stored in the database with the given
     * quantity of the exercise to be done
     * @param workout the workout to add an exercise to
     * @param exercise the exercise to add to the workout
     *
     * @return true if exercise was added successfully, false otherwise
     */
    public boolean addExerciseToWorkout (Workout workout, WorkoutExerciseDuration exercise);
}
