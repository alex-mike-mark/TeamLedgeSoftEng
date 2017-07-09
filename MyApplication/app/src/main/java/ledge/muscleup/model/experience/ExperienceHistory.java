package ledge.muscleup.model.experience;

import org.joda.time.LocalDate;

/**
 * A history of all workouts completed in a given time period and the current experience level of
 * the user
 *
 * @author Cole Kehler
 * @version 3.0
 * @since 2017-07-08
 */

public class ExperienceHistory {
    private CompletedWorkoutRecord[] completedWorkoutRecordList;
    private LevelProgress currentLevelProgress;

    /**
     * The default constructor for the ExperienceHistory class
     * @param completedWorkoutRecordList a list of completed workout records
     */
    public ExperienceHistory(CompletedWorkoutRecord[] completedWorkoutRecordList) {
        this.currentLevelProgress = new LevelProgress(completedWorkoutRecordList[0]);
        this.completedWorkoutRecordList = completedWorkoutRecordList;
    }

    /**
     * Gets the number of workouts completed in some number of previous days
     * @param numDays the number of days to check for completed workouts
     * @return the amount of
     */
    public int getNumWorkoutsCompleted(int numDays) {
        int i = 0;
        int numWorkoutsCompleted = 0;
        LocalDate endDate = new LocalDate().minusDays(numDays);

        while (completedWorkoutRecordList[i++].getDateOfCompletion().isAfter(endDate))
            numWorkoutsCompleted++;

        return numWorkoutsCompleted;
    }

    /**
     * Gets the amount of experience gained in some number of previous days
     * @param numDays the number of days to check the amount of XP gained for
     * @return the amount of experience gained
     */
    public int getXPGained(int numDays) {
        int i = 0;
        int xpGained = 0;
        LocalDate endDate = new LocalDate().minusDays(numDays);

        while (completedWorkoutRecordList[i].getDateOfCompletion().isAfter(endDate))
            xpGained += completedWorkoutRecordList[i++].getExperienceGained();

        return xpGained;
    }

    /**
     * Gets the number of levels gained in some number of previous days
     * @param numDays the number of days to check the level difference for
     * @return the number of levels gained
     */
    public int getLevelsGained(int numDays) {
        int i = 0;
        int levelsGained = 0;
        LocalDate endDate = new LocalDate().minusDays(numDays);
        LevelProgress originalProgress;

        while (completedWorkoutRecordList[i].getDateOfCompletion().isAfter(endDate))
            i++;

        originalProgress = new LevelProgress(completedWorkoutRecordList[--i]);

        return currentLevelProgress.getCurrLevel() - originalProgress.getCurrLevel();
    }

    /**
     * Gets the current level of the user
     * @return the current level of the user
     */
    public int getCurrLevel() {
        return currentLevelProgress.getCurrLevel();
    }

    /**
     * Gets the progress towards the next level
     * @return the progress towards the next level
     */
    public int getNextLevelXPProgress() {
        return currentLevelProgress.getNextLevelXPProgress();
    }

    /**
     * Gets the experience needed to reach the next level
     * @return the experience needed to reach the next level
     */
    public int getNextLevelXPTotal() {
        return currentLevelProgress.getNextLevelXPTotal();
    }
}
