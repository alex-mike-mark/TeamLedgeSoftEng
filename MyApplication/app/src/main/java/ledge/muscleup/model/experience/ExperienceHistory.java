package ledge.muscleup.model.experience;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import static java.util.Collections.enumeration;

/**
 * A history of all workouts completed in a given time period and the current experience level of
 * the user
 *
 * @author Cole Kehler
 * @version 3.0
 * @since 2017-07-08
 */

public class ExperienceHistory {
    private List<CompletedWorkoutRecord> completedWorkoutRecordList;
    private LevelProgress currentLevelProgress;

    /**
     * The default constructor for the ExperienceHistory class
     * @param completedWorkoutRecordList a list of completed workout records
     */
    public ExperienceHistory(List<CompletedWorkoutRecord> completedWorkoutRecordList) {
        if (completedWorkoutRecordList != null && !completedWorkoutRecordList.isEmpty()) {
            this.currentLevelProgress = new LevelProgress(completedWorkoutRecordList.get(0));
            this.completedWorkoutRecordList = completedWorkoutRecordList;
        }
        else {
            this.currentLevelProgress = new LevelProgress(null);
            this.completedWorkoutRecordList = new ArrayList<>();
        }
    }

    /**
     * Gets the number of workouts completed in some number of previous days
     * @param numDays the number of days to check for completed workouts
     * @return the amount of
     */
    public int getNumWorkoutsCompleted(int numDays) {
        int numWorkoutsCompleted = 0;
        LocalDate endDate = new LocalDate().minusDays(numDays);
        Iterator<CompletedWorkoutRecord> iterator = completedWorkoutRecordList.iterator();

        while (iterator.hasNext() && iterator.next().getDateOfCompletion().isAfter(endDate))
            numWorkoutsCompleted++;

        return numWorkoutsCompleted;
    }

    /**
     * Gets the amount of experience gained in some number of previous days
     * @param numDays the number of days to check the amount of XP gained for
     * @return the amount of experience gained
     */
    public int getXPGained(int numDays) {
        int xpGained = 0;
        LocalDate endDate = new LocalDate().minusDays(numDays);
        boolean beforeEndDate = false;
        CompletedWorkoutRecord completedWorkoutRecord;
        Iterator<CompletedWorkoutRecord> iterator = completedWorkoutRecordList.iterator();

        while (iterator.hasNext() && !beforeEndDate) {
            completedWorkoutRecord = iterator.next();
            if (!completedWorkoutRecord.getDateOfCompletion().isAfter(endDate))
                beforeEndDate = true;
            else
                xpGained += completedWorkoutRecord.getExperienceGained();
        }

        return xpGained;
    }

    /**
     * Gets the number of levels gained in some number of previous days
     * @param numDays the number of days to check the level difference for
     * @return the number of levels gained
     */
    public int getLevelsGained(int numDays) {
        LocalDate endDate = new LocalDate().minusDays(numDays);
        boolean beforeEndDate = false;
        CompletedWorkoutRecord completedWorkoutRecord;
        CompletedWorkoutRecord prevCompletedWorkoutRecord = null;
        int levelsGained = 0;
        Iterator<CompletedWorkoutRecord> iterator = completedWorkoutRecordList.iterator();

        while (iterator.hasNext() && !beforeEndDate) {
            completedWorkoutRecord = iterator.next();
            if (!completedWorkoutRecord.getDateOfCompletion().isAfter(endDate))
                beforeEndDate = true;
            else
                prevCompletedWorkoutRecord = completedWorkoutRecord;
        }

        if (prevCompletedWorkoutRecord != null)
            levelsGained = currentLevelProgress.getCurrLevel() - new LevelProgress(prevCompletedWorkoutRecord).getCurrLevel();

        return levelsGained;
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

    /**
     * Returns an enumeration for traversing over the CompletedWorkoutRecords in the history
     * @return an enumeration of the CompletedWorkoutRecords
     */
    public Enumeration<CompletedWorkoutRecord> getCompletedWorkoutsEnumeration() {
        return enumeration(completedWorkoutRecordList);
    }
}
