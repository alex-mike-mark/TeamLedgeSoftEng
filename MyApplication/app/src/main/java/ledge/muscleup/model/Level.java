package ledge.muscleup.model;

/**
 * Created by Ryan on 2017-06-27.
 */

public class Level {
    private int levelNumber;
    private int xpRequired;

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
        this.xpRequired = 0;
        for (int i = levelNumber; i > 0; i--) {
            this.xpRequired += (i * 50);
        }
    }

    public int getLevelNumber() {
        return this.levelNumber;
    }

    public int getXpRequired() {
        return this.xpRequired;
    }
}
