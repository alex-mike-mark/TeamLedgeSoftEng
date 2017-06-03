package ledge.muscleup.model.workout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import ledge.muscleup.model.exercise.InterfaceExercise;

/**
 * Stores information about workouts, which consists of a set of exercises, a list of required
 * equipment, muscle groups worked, sets/reps or duration for each exercise and a workout type
 *
 * @author Alexander Mark
 * @version 1.0
 * @since 2017-05-27
 */
public abstract class Workout implements InterfaceWorkout {
    protected String name;
    protected List<InterfaceExercise> exerciseList;
    private Iterator<InterfaceExercise> exerciseListIterator;

    protected Workout(String name) {
        this.name = name;
        exerciseList = new ArrayList<>();
    }

    /**
     * Returns the name of the workout
     *
     * @return the name of the workout
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Initializes the iterator for the list of exercises to the first InterfaceExercise in the list
     */
    @Override
    public void initExerciseIteration() {
        exerciseListIterator = exerciseList.iterator();
    }

    /**
     * Returns {@code true} if there is another InterfaceExercise in the list or {@code false} if not
     *
     * @return a boolean representing whether the iterator is at the end of the list or not
     */
    @Override
    public boolean hasNextExercise() {
        return exerciseListIterator.hasNext();
    }

    /**
     * Returns the next InterfaceExercise in the list
     *
     * @return the next InterfaceExercise in the list
     * @throws NoSuchElementException if the iterator is at the end of the list
     */
    @Override
    public InterfaceExercise nextExercise() throws NoSuchElementException {
        try {
            return exerciseListIterator.next();
        }
        catch(NoSuchElementException nsee) {
            throw nsee;
        }
    }
}
