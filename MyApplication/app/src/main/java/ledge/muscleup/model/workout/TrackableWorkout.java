package ledge.muscleup.model.workout;

import java.util.Date;

import ledge.muscleup.model.exercise.InterfaceExerciseQuantity;
import ledge.muscleup.model.exercise.InterfaceTrackableExercise;

/**
 * A workout for which progress can be logged
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-03
 */

public class TrackableWorkout extends Workout implements InterfaceTrackableWorkout {
    private Date scheduledDate;

    /**
     * The default constructor for the TrackableWorkout class
     * @param name the name of the workout
     * @param scheduledDate the scheduled date of the workout
     * @param exercises an array of TrackableExercises for the workout
     */
    public TrackableWorkout(String name, Date scheduledDate, InterfaceTrackableExercise[] exercises) {
        super(name);
        this.scheduledDate = scheduledDate;
        for (int i = 0; i < exercises.length; i++)
            exerciseList.add(exercises[i]);
    }

    /**
     * Returns the scheduled date of the workout
     *
     * @return the scheduled date of the workout
     */
    @Override
    public Date getDate() {
        return scheduledDate;
    }

    /**
     * Sets the scheduled date of the workout
     *
     * @param newDate the new date of the workout
     */
    @Override
    public void setDate(Date newDate) {
        scheduledDate = newDate;
    }

    /**
     * Log a quantity of exercise for one of the exercises in the workout
     *
     * @param exercise the exercise to update the quantity for
     * @param quantity the amount of exercise to log
     * @return a boolean representing if the exercise was found and updated in the workout
     */
    @Override
    public boolean logExercise(InterfaceTrackableExercise exercise, InterfaceExerciseQuantity quantity) {
        boolean exerciseLogged = false;
        int exerciseIndex = exerciseList.indexOf(exercise);
        InterfaceTrackableExercise listExercise;

        //ensure the exercise exists in the list
        if (exerciseIndex != -1)
        {
            listExercise = (InterfaceTrackableExercise)(exerciseList.get(exerciseIndex));
            exerciseLogged = listExercise.updateTrackedQuantity(quantity);
        }

        return exerciseLogged;
    }

    /**
     * Compares the current InterfaceWorkout to another instance of InterfaceWorkout
     *
     * @param other the instance of InterfaceWorkout to compare to
     * @return a boolean representing whether the two instances were equal
     */
    @Override
    public boolean equals(InterfaceWorkout other) {
        TrackableWorkout otherWorkout;
        boolean isEqual = false;

        if (other instanceof TrackableWorkout) {
            otherWorkout = (TrackableWorkout) other;
            if (this.name.equals(otherWorkout.getName())) {
                isEqual = true;
            }
        }

        return isEqual;
    }

    /**
     *  Returns the TrackableWorkout as a String
     * @return the TrackableWorkout as a String
     */
    @Override
    public String toString()
    {
        String result = "";

        result += scheduledDate + ": " + name + "\n";
        for (int i = 0; i < exerciseList.size(); i++)
            result += " " + (i + 1) + ". " + exerciseList.get(i) + "\n";

        return result;
    }
}
