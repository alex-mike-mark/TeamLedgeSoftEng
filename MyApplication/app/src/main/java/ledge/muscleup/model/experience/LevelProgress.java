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
    private static final int XP_INCREASE_PER_LEVEL = 500;

    private int currLevel;
    private int nextLevelXPProgress;
    private int nextLevelXPTotal;

    /**
     * The default constructor for the LevelProgress class
     * @param mostRecentWorkoutRecords the most recent completed workout
     */
    public LevelProgress(CompletedWorkoutRecord mostRecentWorkoutRecords) {
        int totalCurrXP;
        int level = 0;

        if (mostRecentWorkoutRecords != null) {
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
        else {
            currLevel = 0;
            nextLevelXPProgress = 0;
            nextLevelXPTotal = XP_INCREASE_PER_LEVEL;
        }
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
