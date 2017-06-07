package ledge.muscleup.model.workout;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import ledge.muscleup.model.exercise.InterfaceExerciseQuantity;
import ledge.muscleup.model.exercise.InterfaceWorkoutExercise;
import ledge.muscleup.model.exercise.InterfaceWorkoutSessionExercise;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.exercise.WorkoutSessionExercise;

import static java.util.Collections.enumeration;

/**
 * A workout that is scheduled and for which progress can be logged
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-03
 */

public class WorkoutSession implements InterfaceWorkoutSession {
    private static final DateTimeFormatter format = DateTimeFormat.forPattern("yyyy/MM/dd");

    private String name;
    private List<InterfaceWorkoutSessionExercise> exerciseList;
    private Iterator<InterfaceWorkoutSessionExercise> exerciseListIterator;
    private LocalDate scheduledDate;
    private boolean isComplete;

    /**
     * The default constructor for the WorkoutSession class
     * @param workout the workout to create a WorkoutSession for
     * @param scheduledDate the date to create the WorkoutSession for
     * @param isComplete whether the session has been completed or not
     */
    public WorkoutSession(Workout workout, LocalDate scheduledDate, boolean isComplete) {
        this.name = workout.getName();
        this.scheduledDate = scheduledDate;
        this.isComplete = isComplete;

        Enumeration<InterfaceWorkoutExercise> enumeration = workout.getExerciseEnumeration();
        while(enumeration.hasMoreElements())
            exerciseList.add(new WorkoutSessionExercise((WorkoutExercise) enumeration.nextElement(), false));
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
     * Returns the scheduled date of the workout
     *
     * @return the scheduled date of the workout
     */
    @Override
    public LocalDate getDate() { return scheduledDate; }

    /**
     * Sets the scheduled date of the workout
     *
     * @param newDate the new date of the workout
     */
    @Override
    public void setDate(LocalDate newDate) { scheduledDate = newDate; }

    /**
     * Returns {@code true} if the worokout has been completed, or {@code false} otherwise
     *
     * @return a boolean representing if the workout has been completed
     */
    @Override
    public boolean isComplete() {
        return isComplete;
    }

    /**
     * Toggles the completed state of this workout
     */
    @Override
    public void toggleCompleted() {
        isComplete = !isComplete;
    }

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
     * Log an exercise as complete
     *
     * @param exercise the exercise to complete
     * @param quantity
     */
    @Override
    public boolean completeExercise(InterfaceWorkoutSessionExercise exercise, InterfaceExerciseQuantity quantity) {
        boolean exerciseCompleted = false;
        int exerciseIndex = exerciseList.indexOf(exercise);
        InterfaceWorkoutSessionExercise listExercise;

        //ensure the exercise exists in the list
        if (exerciseIndex != -1)
        {
            listExercise = exerciseList.get(exerciseIndex);
            if (!listExercise.isComplete()) {
                listExercise.toggleCompleted();
                exerciseCompleted = true;
            }
        }

        return exerciseCompleted;
    }

    /**
     * Returns an enumeration for traversing over the exercises in the workout
     *
     * @return an enumeration of the exercises
     */
    @Override
    public Enumeration<InterfaceWorkoutSessionExercise> getExerciseEnumeration() {
        return enumeration(exerciseList);
    }

    /**
     * Compares the current WorkoutSession to another instance of InterfaceWorkoutSession
     *
     * @param other the instance of InterfaceWorkoutSession to compare to
     * @return a boolean representing whether the two instances were equal
     */
    @Override
    public boolean equals(InterfaceWorkoutSession other) {
        Enumeration<InterfaceWorkoutSessionExercise> thisEnumeration = getExerciseEnumeration();
        Enumeration<InterfaceWorkoutSessionExercise> otherEnumeration = other.getExerciseEnumeration();
        boolean exercisesEqual = true;

        if(numExercises() != other.numExercises())
            exercisesEqual = false;
        while(thisEnumeration.hasMoreElements() && otherEnumeration.hasMoreElements() && exercisesEqual)
            exercisesEqual = thisEnumeration.nextElement().equals(otherEnumeration.nextElement());

        return (exercisesEqual &&
                name.equals(other.getName()) &&
                this.scheduledDate.equals(other.getDate()) &&
                this.isComplete == other.isComplete());
    }

    /**
     * Returns the WorkoutSession as a String
     * @return the WorkoutSession as a String
     */
    @Override
    public String toString() {
        String result = "";

        result += format.print(scheduledDate) + ": " + name + "\n";
        for (int i = 0; i < exerciseList.size(); i++)
            result += " " + (i + 1) + ". " + exerciseList.get(i).toString() + "\n";

        return result;
    }
}