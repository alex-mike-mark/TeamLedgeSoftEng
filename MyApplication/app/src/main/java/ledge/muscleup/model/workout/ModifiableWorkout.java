package ledge.muscleup.model.workout;

import ledge.muscleup.model.exercise.InterfaceExerciseQuantity;
import ledge.muscleup.model.exercise.InterfaceSuggestedExercise;

/**
 * A modifiable workout, where the name, favourite status, list of exercises and the recommended
 * quantity for each exercise can be altered
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-03
 */

public class ModifiableWorkout extends Workout implements InterfaceModifiableWorkout {
    private boolean isFavourite;

    /**
     * The constructor for the ModifiableWorkout class which can be assigned favourite
     * @param name the name of the workout
     * @param isFavourite if the workout is a favourite workout
     * @param exercises an array of SuggestedExercises for the workout
     */
    protected ModifiableWorkout(String name, boolean isFavourite, InterfaceSuggestedExercise[] exercises) {
        super(name);
        this.isFavourite = isFavourite;
        for (int i = 0; i < exercises.length; i++)
            exerciseList.add(exercises[i]);
    }

    /**
     * The constructor for the ModifiableWorkout class which is not favourite by devault
     * @param name the name of the workout
     * @param exercises an array of SuggestedExercises for the workout
     */
    protected ModifiableWorkout(String name, InterfaceSuggestedExercise[] exercises) {
        super(name);
        this.isFavourite = false;
        for (int i = 0; i < exercises.length; i++)
            exerciseList.add(exercises[i]);
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
    public boolean setRecommendedQuantity(InterfaceSuggestedExercise exercise, InterfaceExerciseQuantity quantity) {
        boolean quantityUpdated = false;
        int exerciseIndex = exerciseList.indexOf(exercise);
        InterfaceSuggestedExercise listExercise;

        //ensure the exercise exists in the list
        if (exerciseIndex != -1)
        {
            listExercise = (InterfaceSuggestedExercise)(exerciseList.get(exerciseIndex));
            quantityUpdated = listExercise.updateRecommendedQuantity(quantity);
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
     * Adds a new exercise to the workout
     *
     * @param exercise the exercise to add to the workout
     */
    @Override
    public void addExercise(InterfaceSuggestedExercise exercise) { exerciseList.add(exercise); }

    /**
     * Move the position of an exercise in the list of exercises
     *
     * @param exercise the exercise to change the position of
     * @param index    the index of the exercise to move
     * @return a boolean representing if the exercise was found and moved to the new index
     */
    @Override
    public boolean moveExercise(InterfaceSuggestedExercise exercise, int index) {
        boolean exerciseMoved = false;
        int exerciseIndex = exerciseList.indexOf(exercise);
        InterfaceSuggestedExercise listExercise;

        //ensure the exercise exists in the list and the index is inside the bounds of the list
        if (exerciseIndex != -1 && (index >= 0 && index <= exerciseList.size())) {
            //only move the exercise if we're given a new place to move to
            if (exerciseIndex != index) {
                //if the exercise is moving down the list, removing the exercise changes the target
                //index, so adjust accordingly
                if (exerciseIndex < index)
                    index--;
                listExercise = (InterfaceSuggestedExercise)(exerciseList.remove(exerciseIndex));
                exerciseList.add(index, listExercise);
            }
            exerciseMoved = true;
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
    public boolean removeExercise(InterfaceSuggestedExercise exercise) { return exerciseList.remove(exercise); }

    /**
     * Compares the current ModifiableWorkout to another instance of InterfaceWorkout
     *
     * @param other the instance of InterfaceWorkout to compare to
     * @return a boolean representing whether the two instances were equal
     */
    @Override
    public boolean equals(InterfaceWorkout other) {
        ModifiableWorkout otherWorkout;
        boolean isEqual = false;

        if (other instanceof ModifiableWorkout) {
            otherWorkout = (ModifiableWorkout) other;
            if (this.name.equals(otherWorkout.getName())) {
                isEqual = true;
            }
        }

        return isEqual;
    }
}
