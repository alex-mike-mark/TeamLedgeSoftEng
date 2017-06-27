package ledge.muscleup.model.workout;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import ledge.muscleup.model.exercise.InterfaceExerciseQuantity;
import ledge.muscleup.model.exercise.WorkoutExercise;

import static java.util.Collections.enumeration;
import static java.util.Collections.lastIndexOfSubList;

/**
 * Stores information about workouts, which consists of a workout name, a set of exercises and
 * whether the workout is a favourite workout
 *
 * @author Alexander Mark
 * @version 1.0
 * @since 2017-05-27
 */
public class Workout {
    private String name;
    private List<WorkoutExercise> exerciseList;
    private boolean isFavourite;

    /**
     * The constructor for the Workout, which creates an empty workout
     * @param name the name of the workout
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public Workout(String name) throws IllegalArgumentException {
        if(name == null) {
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else {
            this.name = name;
            this.isFavourite = false;
            exerciseList = new ArrayList<>();
        }
    }

    /**
     * The constructor for the Workout, which creates a Workout based on a list of exercises
     * @param name the name of the workout
     * @param isFavourite if the workout is a favourite workout
     * @param exercises the list of exercises that make up the workout
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public Workout(String name, boolean isFavourite, WorkoutExercise[] exercises) throws IllegalArgumentException {
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
    public String getName() {
        return name;
    }

    /**
     * Set the name of the workout
     *
     * @param newName the new name for the workout
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Sets the recommended quantity of exercise for a given exercise in the workout
     *
     * @param exercise the exercise to set the quantity for
     * @param quantity the quantity to assign to the exercise
     * @throws IllegalArgumentException if passed a {@code null} parameter
     * @return a boolean representing if the exercise was found and updated in the workout
     */
    public boolean setRecommendedQuantity(WorkoutExercise exercise,
                                          InterfaceExerciseQuantity quantity) throws IllegalArgumentException {
        boolean quantityUpdated = false;
        int exerciseIndex = -1;

        if (exercise == null || quantity == null)
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else {
            for(int i = 0; i < exerciseList.size() && exerciseIndex < 0; i++){
                if(exercise.equals(exerciseList.get(i))){
                    exerciseIndex = i;
                }
            }

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
    public boolean isFavourite() { return isFavourite; }

    /**
     * Toggle the favourite status of this workout
     */
    public void toggleFavourite() {
        isFavourite = !isFavourite;
    }

    /**
     * Returns the number of exercises in the workout
     *
     * @return the number of exercises in the workout
     */
    public int numExercises() {
        return exerciseList.size();
    }

    /**
     * Adds a new exercise to the workout
     *
     * @param exercise the exercise to add to the workout
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public void addExercise(WorkoutExercise exercise) throws IllegalArgumentException {
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
     * @throws IllegalArgumentException if passed a {@code null} parameter or if {@code index} is
     * outside the bounds of the list of exercises
     * @return a boolean representing if the exercise was found and moved to the new index
     */
    public boolean moveExercise(WorkoutExercise exercise,
                                int index) throws IllegalArgumentException {
        boolean exerciseMoved = false;
        int exerciseIndex = -1;
        WorkoutExercise listExercise;
        int listSize = exerciseList.size();

        if (exercise == null || index <= 0 || index > listSize)
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else {
            for(int i = 0; i < exerciseList.size() && exerciseIndex < 0; i++){
                if(exercise.equals(exerciseList.get(i))){
                    exerciseIndex = i;
                }
            }

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
     * @throws IllegalArgumentException if passed a {@code null} parameter
     * @return the exercise that was removed, or {@code null} if the exercise couldn't be found
     */
    public boolean removeExercise(WorkoutExercise exercise) throws IllegalArgumentException {
        boolean exerciseRemoved = false;
        int exerciseIndex = -1;
        WorkoutExercise listExercise;

        if (exercise == null)
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else {
            for(int i = 0; i < exerciseList.size() && exerciseIndex < 0; i++){
                if(exercise.equals(exerciseList.get(i))){
                    exerciseIndex = i;
                }
            }

            //ensure the exercise exists in the list
            if (exerciseIndex != -1) {
                exerciseList.remove(exerciseIndex);
                exerciseRemoved = true;
            }

            return exerciseRemoved;
        }
    }

    /**
     * Returns an enumeration for traversing over the exercises in the workout
     */
    public Enumeration<WorkoutExercise> getExerciseEnumeration() {
        return enumeration(exerciseList);
    }

    /**
     * Compares the current Workout to another instance of Workout
     *
     * @param other the instance of Workout to compare to
     * @return a boolean representing whether the two instances were equal
     */

    public boolean equals(Workout other) {
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

    public List<WorkoutExercise> getExerciseList() {
        return exerciseList;
    }

    /**
     * Returns the experience value of a workout, which is the sum of the xp values of its exercises
     * @return
     */
    public int getExperienceValue() {
        int total = 0;
        for (WorkoutExercise exercise: exerciseList) {
            total+= exercise.getExperienceValue();
        }
        return total;
    }
}
