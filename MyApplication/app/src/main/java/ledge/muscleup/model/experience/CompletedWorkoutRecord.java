package ledge.muscleup.model.experience;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

/**
 * A record of a completed workout, which contains the date the workout was completed, the experience
 * gained from completing the workout, the experience value after completing the workout and the
 * name of the workout completed
 *
 * @author Cole Kehler
 * @version 3.0
 * @since 2017-07-08
 */

public class CompletedWorkoutRecord {
    private String workoutName;
    private int experienceGained;
    private int experienceAfterCompletion;
    private LocalDateTime dateOfCompletion;

    /**
     * The default constructor for a CompletedWorkoutRecord
     * @param workoutName the name of the workout
     * @param experienceBeforeCompletion the experience before completing this workout
     * @param experienceAfterCompletion the experience after completing this workout
     * @param dateOfCompletion the date the workout was completed
     */
    public CompletedWorkoutRecord(String workoutName, int experienceBeforeCompletion,
                                  int experienceAfterCompletion, LocalDateTime dateOfCompletion) {
        if (workoutName == null || dateOfCompletion == null)
            throw(new IllegalArgumentException("Invalid or null data passed to a method"));
        else {
            this.workoutName = workoutName;
            this.experienceAfterCompletion = experienceAfterCompletion;
            this.experienceGained = experienceAfterCompletion - experienceBeforeCompletion;
            this.dateOfCompletion = dateOfCompletion;
        }
    }

    /**
     * Returns the name of the workout
     * @return the name of the workout
     */
    public String getWorkoutName() {
        return workoutName;
    }

    /**
     * Returns the experience gained from completing this workout
     * @return the experience gained from completing this workout
     */
    public int getExperienceGained() {
        return experienceGained;
    }

    /**
     * Returns the experience gained after completing this workout
     * @return the experience gained after completing this workout
     */
    public int getExperienceAfterCompletion() {
        return experienceAfterCompletion;
    }

    /**
     * Returns the date this workout was completed
     * @return the date this workout was completed
     */
    public LocalDate getDateOfCompletion() {
        return dateOfCompletion.toLocalDate();
    }

    /**
     * Returns {@code true} if the CompletedWorkoutRecords are equal, or {@code false} otherwise
     * @return a boolean representing if the items are equal
     */
    public boolean equals(CompletedWorkoutRecord other) {
        return workoutName.equals(other.getWorkoutName()) && experienceGained == other.getExperienceGained() &&
                experienceAfterCompletion == other.getExperienceAfterCompletion() &&
                getDateOfCompletion().equals(other.getDateOfCompletion());
    }
}
