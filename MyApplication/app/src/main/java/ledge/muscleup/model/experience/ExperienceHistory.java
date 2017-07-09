package ledge.muscleup.model.experience;

import org.joda.time.LocalDate;

import java.util.Enumeration;
import java.util.NoSuchElementException;

import static java.util.Collections.enumeration;

/**
 * A history of all workouts completed in a given time period and the current experience level of
 * the user
 *
 * @author Cole Kehler
 * @version 3.0
 * @since 2017-07-08
 */

public class ExperienceHistory implements Enumeration {
    private CompletedWorkoutRecord[] completedWorkoutRecordList;
    private LevelProgress currentLevelProgress;
    private int currElement;

    /**
     * The default constructor for the ExperienceHistory class
     * @param completedWorkoutRecordList a list of completed workout records
     */
    public ExperienceHistory(CompletedWorkoutRecord[] completedWorkoutRecordList) {
        this.currentLevelProgress = new LevelProgress(completedWorkoutRecordList[0]);
        this.completedWorkoutRecordList = completedWorkoutRecordList;
        this.currElement = 0;
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

    /**
     * Initializes an enumeration of CompletedWorkoutRecords
     */
    public void initEnumeration() {
        currElement = 0;
    }

    /**
     * Tests if this enumeration contains more elements.
     *
     * @return <code>true</code> if and only if this enumeration object
     * contains at least one more element to provide;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean hasMoreElements() {
        return currElement < completedWorkoutRecordList.length;
    }

    /**
     * Returns the next element of this enumeration if this enumeration
     * object has at least one more element to provide.
     *
     * @return the next element of this enumeration.
     * @throws NoSuchElementException if no more elements exist.
     */
    @Override
    public Object nextElement() {
        if (!hasMoreElements())
            throw new NoSuchElementException();
        else
            return completedWorkoutRecordList[currElement++];
    }
}
