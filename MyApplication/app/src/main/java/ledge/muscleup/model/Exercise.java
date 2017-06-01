package ledge.muscleup.model;

/**
 * Created by Alexander on 2017-05-25.
 */

public class Exercise {
    static private int nextID = 0;

    private int id;
    private String name;
    private int intensity;
    private String muscles;
    private boolean isFavourite;

    public Exercise(String name, int intensity, String muscles){
        id = nextID;
        nextID++;
        this.name = name;
        this.intensity = intensity;
        this.muscles = muscles;
        isFavourite = false;
    }

    /**
     * equals
     * Current equals method, just checks exercise name is all.
     *
     * @param other is the other exercise.
     * @return returns if the exercise is equal.
     */
    public boolean equals(Exercise other){
        return this.id==other.id;
    }

    /**
     * Method to change the value of isFavourite.
     */
    public void toggleFavourite(){
        isFavourite=!isFavourite;
    }

    /**
     * Returns if the workout is a favourite.
     * @return
     */
    public boolean isFavourite(){
        return isFavourite;
    }

    /**
     * This is literally a getter
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * This is literally a getter
     * @return
     */
    public int getIntensity(){
        return intensity;
    }

    /**
     * This is literally a getter
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * This is literally a getter
     * @return
     */
    public String getMusclesWorked(){
        return muscles;
    }
}
