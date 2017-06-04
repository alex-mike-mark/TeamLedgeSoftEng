package ledge.muscleup.model;

/**
 * Created by koope on 2017-05-29.
 */
import junit.framework.TestCase;

import ledge.muscleup.model.exercise.DistanceUnit;
import ledge.muscleup.model.exercise.ExerciseDistance;

public class ExerciseDistanceTest extends TestCase {

    public ExerciseDistanceTest (String arg0) {
        super(arg0);
    }

    public void testExerciseDistance1() {
        ExerciseDistance exerciseDistance;

        System.out.println ("Starting testExerciseDistance");

        exerciseDistance = new ExerciseDistance(2.5, DistanceUnit.MILES);
        assertNotNull(exerciseDistance);
        assertTrue(exerciseDistance.getDistance() == 2.5);
        assertTrue(exerciseDistance.getUnitOfMeasure() == DistanceUnit.MILES);

        System.out.println("Finished testExerciseDistance");
    }
}
