package ledge.muscleup.model.workout;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import ledge.muscleup.model.exercise.InterfaceExerciseQuantity;
import ledge.muscleup.model.exercise.InterfaceWorkoutExercise;

import static java.util.Collections.enumeration;

/**
 * Stores information about workouts, which consists of a workout name, a set of exercises, whether
 * the workout is a favourite workout and whether the workout is completed
 *
 * @author Alexander Mark
 * @version 1.0
 * @since 2017-05-27
 */
public class Workout implements InterfaceWorkout {
    private String name;
    private List<InterfaceWorkoutExercise> exerciseList;
    private Iterator<InterfaceWorkoutExercise> exerciseListIterator;
    private boolean isFavourite;

    public Workout(String name, boolean isFavourite) {
        if(name == null) {
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else {
            this.name = name;
            this.isFavourite = isFavourite;
            exerciseList = new ArrayList<>();
        }
    }

    public Workout(String name, boolean isFavourite, InterfaceWorkoutExercise[] exercises) {
        if (name == null || exercises == null) {
            throw (new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else {
            this.name = name;
            this.isFavourite = isFavourite;

            exerciseList = new ArrayList<>();
            for (int i = 0; i < exercises.length; i++)
                exerciseList.add(exercises[i]);
        }
    }

    /**
     * Returns the name of the workout
     *
     * @return the name of the workout
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Set the name of the workout
     *
     * @param newName the new name for the workout
     */
    @Override
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Sets the recommended quantity of exercise for a given exercise in the workout
     *
     * @param exercise the exercise to set the quantity for
     * @param quantity the quantity to assign to the exercise
     * @return a boolean representing if the exercise was found and updated in the workout
     */
    @Override
    public boolean setRecommendedQuantity(InterfaceWorkoutExercise exercise, InterfaceExerciseQuantity quantity) {
        boolean quantityUpdated = false;
        int exerciseIndex;

        if (exercise == null || quantity == null)
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else {
            exerciseIndex = exerciseList.indexOf(exercise);

            //ensure the exercise exists in the list
            if (exerciseIndex != -1)
                quantityUpdated = exerciseList.get(exerciseIndex).updateRecommendedQuantity(quantity);
        }

        return quantityUpdated;
    }

    /**
     * Returns {@code true} if the workout is a favourite workout, and {@code false} otherwise
     *
     * @return a boolean represeting whether this workout is a favourite workout
     */
    @Override
    public boolean isFavourite() { return isFavourite; }

    /**
     * Toggle the favourite status of this workout
     */
    @Override
    public void toggleFavourite() { isFavourite = !isFavourite; }

    /**
     * Returns the number of exercises in the workout
     *
     * @return the number of exercises in the workout
     */
    @Override
    public int numExercises() {
        return exerciseList.size();
    }

    /**
     * Adds a new exercise to the workout
     *
     * @param exercise the exercise to add to the workout
     */
    @Override
    public void addExercise(InterfaceWorkoutExercise exercise) {
        if (exercise == null)
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else
            exerciseList.add(exercise);
    }

    /**
     * Move the position of an exercise in the list of exercises
     *
     * @param exercise the exercise to change the position of
     * @param index    the index of the exercise to move
     * @return a boolean representing if the exercise was found and moved to the new index
     */
    @Override
    public boolean moveExercise(InterfaceWorkoutExercise exercise, int index) {
        boolean exerciseMoved = false;
        int exerciseIndex;
        InterfaceWorkoutExercise listExercise;

        if (exercise == null || (index >= 0 && index < numExercises()))
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else {
            exerciseIndex = exerciseList.indexOf(exercise);

            //ensure the exercise exists in the list
            if (exerciseIndex != -1) {
                //only move the exercise if we're given a new place to move to
                if (exerciseIndex != index) {
                    //if the exercise is moving down the list, removing the exercise changes the target
                    //index, so adjust accordingly
                    if (exerciseIndex < index)
                        index--;
                    listExercise = exerciseList.remove(exerciseIndex);
                    exerciseList.add(index, listExercise);
                }
                exerciseMoved = true;
            }
        }
        return exerciseMoved;
    }

    /**
     * Removes an exercise from the list of exercises
     *
     * @param exercise the exercise to remove from the list
     * @return the exercise that was removed, or {@code null} if the exercise couldn't be found
     */
    @Override
    public boolean removeExercise(InterfaceWorkoutExercise exercise) {
        if (exercise == null)
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else
            return exerciseList.remove(exercise);
    }

    /**
     * Returns an enumeration for traversing over the exercises in the workout
     */
    @Override
    public Enumeration<InterfaceWorkoutExercise> getExerciseEnumeration() {
        return enumeration(exerciseList);
    }

    /**
     * Compares the current Workout to another instance of InterfaceWorkout
     *
     * @param other the instance of InterfaceWorkout to compare to
     * @return a boolean representing whether the two instances were equal
     */
    @Override
    public boolean equals(InterfaceWorkout other) {
        return other != null && this.name.equals(other.getName());
    }

    /**
     * Returns the Workout as a String
     *
     * @return the Workout as a String
     */
    @Override
    public String toString() {
        String result = "";

        result += name + "\n";
        for (int i = 0; i < exerciseList.size(); i++)
            result += " " + (i + 1) + ". " + exerciseList.get(i).toString() + "\n";

        return result;
    }
}
