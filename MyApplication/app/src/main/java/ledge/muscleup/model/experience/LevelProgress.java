package ledge.muscleup.model.experience;

/**
 * Holds all information pertaining to the current level of the user, including the level number,
 * the amount of experience towards the next level, and the experience needed to reach the next level
 *
 * @author Cole Kehler
 * @version 3.0
 * @since 2017-07-08
 */

public class LevelProgress {
    static final int XP_INCREASE_PER_LEVEL = 500;

    private CompletedWorkoutRecord mostRecentWorkoutRecords;
    int currLevel;
    int nextLevelXPProgress;
    int nextLevelXPTotal;

    /**
     * The default constructor for the LevelProgress class
     * @param mostRecentWorkoutRecords the most recent completed workout
     */
    public LevelProgress(CompletedWorkoutRecord mostRecentWorkoutRecords) {
        int totalCurrXP;
        int level = 0;

        this.mostRecentWorkoutRecords = mostRecentWorkoutRecords;

        //calculate the current level, the progress towards the next level and the xp needed to reach
        //the next level
        totalCurrXP = mostRecentWorkoutRecords.getExperienceAfterCompletion();
        do {
            totalCurrXP -= XP_INCREASE_PER_LEVEL * level++;
        } while (totalCurrXP > XP_INCREASE_PER_LEVEL * level);

        currLevel = level;
        nextLevelXPProgress = totalCurrXP;
        nextLevelXPTotal = XP_INCREASE_PER_LEVEL * level;
    }

    /**
     * Returns the current level of the user
     * @return the current level of the user
     */
    public int getCurrLevel() {
        return currLevel;
    }

    /**
     * Returns the progress towards the next level
     * @return the progress towards the next level
     */
    public int getNextLevelXPProgress() {
        return nextLevelXPProgress;
    }

    /**
     * Returns the experience needed to reach the next level
     * @return the experience needed to reach the next level
     */
    public int getNextLevelXPTotal() {
        return nextLevelXPTotal;
    }
}
