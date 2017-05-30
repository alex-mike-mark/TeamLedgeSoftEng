package ledge.muscleup;

/**
 * Created by koope on 2017-05-29.
 */
import junit.framework.TestCase;

import ledge.muscleup.ExerciseDistance;

public class ExerciseDistanceTest extends TestCase {

    public ExerciseDistanceTest (String arg0) {
        super(arg0);
    }

    public void testExerciseDistance1() {
        ExerciseDistance exerciseDistance;

        System.out.println ("Starting testExerciseDistance");

        exerciseDistance = new ExerciseDistance(2.5, "Miles");
        assertNotNull(exerciseDistance);
        assertTrue(exerciseDistance.getDistance() == 2.5);
        assertTrue(exerciseDistance.getUnitOfMeasure().equals("Miles"));

        System.out.println("Finished testExerciseDistance");
    }
}
