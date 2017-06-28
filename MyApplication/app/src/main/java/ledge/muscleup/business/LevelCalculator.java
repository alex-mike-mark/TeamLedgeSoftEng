package ledge.muscleup.business;

import android.util.Log;

import ledge.muscleup.model.Level;

/**
 * Created by Ryan on 2017-06-27.
 */

public class LevelCalculator {
    public static int calculateLevel(int experiencePoints) {
        int levelNum = 0;
        boolean isLevel = false;
        while (!isLevel) {
            Level currLevel = new Level(levelNum);
            Log.d("CurrLevel", String.valueOf(currLevel.getLevelNumber()) + " " + String.valueOf(currLevel.getXpRequired()));
            Level nextLevel = new Level(levelNum + 1);
            Log.d("NextLevel", String.valueOf(nextLevel.getLevelNumber()) + " " + String.valueOf(nextLevel.getXpRequired()));
            if (currLevel.getXpRequired() <= experiencePoints && nextLevel.getXpRequired() > experiencePoints) {
                isLevel = true;
            } else {
                levelNum++;
            }
        }
        return levelNum;
    }

    public static int calculateXPTillNextLevel(int currLevelNum, int experiencePoints) {
        Level nextLevel = new Level(currLevelNum + 1);
        return nextLevel.getXpRequired() - experiencePoints;
    }
}
