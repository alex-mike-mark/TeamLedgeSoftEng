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

    public boolean equals(Exercise other){
        return this.name.equalsIgnoreCase(other.name);
    }

    public void toggleFavourite(){
        isFavourite=!isFavourite;
    }

    public boolean isFavourite(){
        return isFavourite;
    }

    public String getName(){
        return name;
    }

    public int getIntensity(){
        return intensity;
    }

    public String getMusclesWorked(){
        return muscles;
    }
}
