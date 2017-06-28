package ledge.muscleup.business;

import java.util.List;

import ledge.muscleup.application.Services;
import ledge.muscleup.model.exercise.InterfaceExerciseQuantity;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.workout.Workout;
import ledge.muscleup.persistence.DataAccessStub;
import ledge.muscleup.persistence.InterfaceDataAccess;

/**
 * This class contains methods for retrieving, adding, and removing workouts from the database, and
 * adding exercises to a workout, by calling the methods defined in the InterfaceDataAccess interface.
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-05
 */
public class AccessWorkouts implements InterfaceAccessWorkouts {
    private InterfaceDataAccess dataAccess;

    /**
     * Constructor for AccessWorkouts, which initializes the dataAccess variable to the stub database
     */
    public AccessWorkouts() {
        dataAccess = (DataAccessStub) Services.getDataAccess();
    }

    /**
     * This method gets a workout from the database with the given name
     * @param workoutName the name of the workout
     * @return a workout from the database with the given name, if it exists. Otherwise, returns null
     */
    public Workout getWorkout(String workoutName) {
        return dataAccess.getWorkout(workoutName);
    }

    /**
     * This method gets a list of workouts in the database
     * @return a list of the workouts stored in the database
     */
    public List<Workout> getWorkoutsList() {
        return dataAccess.getWorkoutsList();
    }

    /**
     * This method gets the names of all workouts in the database in the form of a list
     * @return a list of workout names in the database
     */
    public List<String> getWorkoutNamesList() {
        return dataAccess.getWorkoutNamesList();
    }

    /**
     * Adds a new workout to the database
     * @param workout the workout to be added to the database
     */
    public void insertWorkout(Workout workout) {
        dataAccess.insertWorkout(workout);
    }

    /**
     * Removes a workout from the database, if it exists
     * @param workout the workout to be removed
     */
    public void removeWorkout(Workout workout) {
        dataAccess.removeWorkout(workout);
    }

    /**
     * Sets the recommended quantity of exercise for a given exercise in a given workout
     *
     * @param workout  the workout that contains the exercise to update
     * @param exercise the exercise to set the quantity for
     * @param quantity the quantity to assign to the exercise
     * @return a boolean representing if the exercise was found and updated in the workout
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public boolean setRecommendedExerciseQuantity(Workout workout, WorkoutExercise exercise, InterfaceExerciseQuantity quantity) throws IllegalArgumentException {
        return workout.setRecommendedQuantity(exercise, quantity) &&
                dataAccess.updateExerciseQuantity(workout, exercise, quantity);
    }

    /**
     * Toggle the favourite status of a workout
     *
     * @param workout the workout to update the status of
     */
    public void toggleWorkoutFavourite(Workout workout) {
        workout.toggleFavourite();
        dataAccess.toggleExerciseFavourite(workout);
    }

    /**
     * Adds an exercise stored in the database to a workout stored in the database with the given
     * quantity of the exercise to be done
     * @param workout the workout to add an exercise to
     * @param exercise the exercise to add to the workout
     *
     * @return true if exercise was added successfully, false otherwise
     */
    public boolean addExerciseToWorkout(Workout workout, WorkoutExercise exercise) {
        boolean exerciseAdded = false; //if the exercise was added

        exerciseAdded = dataAccess.addExerciseToWorkout(workout, exercise);
        if (exerciseAdded)
            workout.addExercise(exercise);

        return exerciseAdded;
    }

    /**
     * Move the position of an exercise in the list of exercises
     *
     * @param workout  the workout to change the order of exercises for
     * @param exercise the exercise to change the position of
     * @param index    the index of the exercise to move
     * @return a boolean representing if the exercise was found and moved to the new index
     * @throws IllegalArgumentException if passed a {@code null} parameter or if {@code index} is
     *                                  outside the bounds of the list of exercises
     */
    public boolean moveWorkoutExercise(Workout workout, WorkoutExercise exercise, int index) throws IllegalArgumentException {
        return workout.moveExercise(exercise, index) &&
                dataAccess.moveWorkoutExercise(workout, exercise, index);
    }

    /**
     * Removes an exercise from the list of exercises
     *
     * @param workout  the workout to remove an exercise from
     * @param exercise the exercise to remove from the list
     * @return the exercise that was removed, or {@code null} if the exercise couldn't be found
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public boolean removeExerciseFromWorkout(Workout workout, WorkoutExercise exercise) throws IllegalArgumentException {
        return workout.removeExercise(exercise) &&
                dataAccess.removeWorkoutExercise(workout, exercise);
    }
}
