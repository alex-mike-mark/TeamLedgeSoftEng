package ledge.muscleup.model.workout;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

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

public class WorkoutSession {
    private static final DateTimeFormatter format = DateTimeFormat.forPattern("yyyy/MM/dd");

    private String name;
    private List<WorkoutSessionExercise> exerciseList;
    private LocalDate scheduledDate;
    private boolean isComplete;

    /**
     * The constructor for the WorkoutSession class, which is created for a given workout
     * @param workout the workout to create a WorkoutSession for
     * @param scheduledDate the date to create the WorkoutSession for
     * @param isComplete whether the session has been completed or not
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public WorkoutSession(Workout workout, LocalDate scheduledDate, boolean isComplete) throws IllegalArgumentException {
        if (workout == null || scheduledDate == null) {
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else {
            this.name = workout.getName();
            this.scheduledDate = scheduledDate;
            this.isComplete = isComplete;
            this.exerciseList = new ArrayList<>();
            Enumeration<WorkoutExercise> enumeration = workout.getExerciseEnumeration();
            while(enumeration.hasMoreElements())
                exerciseList.add(new WorkoutSessionExercise(enumeration.nextElement(), false));
        }
    }

    /**
     * The constructor for the WorkoutSession class, which is created with no workout
     * @param scheduledDate the date to create the WorkoutSession for
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public WorkoutSession(LocalDate scheduledDate) throws IllegalArgumentException {
        if (scheduledDate == null) {
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else {
            this.scheduledDate = scheduledDate;

            this.name = null;
            this.isComplete = false;
            this.exerciseList = new ArrayList<>();
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
     * Returns the scheduled date of the workout
     *
     * @return the scheduled date of the workout
     */
    public LocalDate getDate() { return scheduledDate; }

    /**
     * Sets the scheduled date of the workout
     *
     * @param newDate the new date of the workout
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public void setDate(LocalDate newDate) throws IllegalArgumentException {
        if (newDate == null)
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else
            scheduledDate = newDate;
    }

    /**
     * Returns {@code true} if the worokout has been completed, or {@code false} otherwise
     *
     * @return a boolean representing if the workout has been completed
     */
    public boolean isComplete() {
        return isComplete;
    }

    /**
     * Toggles the completed state of this workout
     */
    public void toggleCompleted() {
        isComplete = !isComplete;
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
     * Log an exercise as complete
     * @param exercise the exercise to complete
     * @throws IllegalArgumentException if passed a {@code null} parameter
     * @return a boolean representing whether the exercise was marked as completed or not
     */
    public boolean completeExercise(WorkoutSessionExercise exercise) throws IllegalArgumentException {
        boolean exerciseCompleted = false;
        int exerciseIndex = -1;
        WorkoutSessionExercise listExercise;

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
                listExercise = exerciseList.get(exerciseIndex);
                if (!listExercise.isComplete()) {
                    listExercise.toggleCompleted();
                    exerciseCompleted = true;
                }
            }
        }

        return exerciseCompleted;
    }

    /**
     * Returns an enumeration for traversing over the exercises in the workout
     *
     * @return an enumeration of the exercises
     */
    public Enumeration<WorkoutSessionExercise> getExerciseEnumeration() {
        return enumeration(exerciseList);
    }

    /**
     * Compares the current WorkoutSession to another instance of WorkoutSession
     *
     * @param other the instance of WorkoutSession to compare to
     * @return a boolean representing whether the two instances were equal
     */
    public boolean equals(WorkoutSession other) {
        Enumeration<WorkoutSessionExercise> thisEnumeration;
        Enumeration<WorkoutSessionExercise> otherEnumeration;
        boolean isEqual = false;
        boolean exercisesEqual = true;

        if (other != null && numExercises() == other.numExercises()) {
            thisEnumeration = getExerciseEnumeration();
            otherEnumeration = other.getExerciseEnumeration();

            while (thisEnumeration.hasMoreElements() && otherEnumeration.hasMoreElements() && exercisesEqual)
                exercisesEqual = thisEnumeration.nextElement().equals(otherEnumeration.nextElement());

            isEqual = (exercisesEqual &&
                    name.equals(other.getName()) &&
                    this.scheduledDate.equals(other.getDate()) &&
                    this.isComplete == other.isComplete());
        }

        return isEqual;
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

    /**
     * Returns the list of workout session exercises
     * @return the list of workout session exercices
     */
    public List<WorkoutSessionExercise> getWorkoutSessionExercises(){
        return exerciseList;
    }

    public int getExperienceValue() {
        int total = 0;
        for (WorkoutSessionExercise sessionExercise: exerciseList) {
            total += sessionExercise.getExperienceValue();
        }

        return total;
    }
}
