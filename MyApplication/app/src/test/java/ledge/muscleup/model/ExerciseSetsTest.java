package ledge.muscleup.model;

/**
 * Created by koope on 2017-05-29.
 */

import junit.framework.TestCase;
import ledge.muscleup.model.exercise.ExerciseSets;

public class ExerciseSetsTest extends TestCase{

    public ExerciseSetsTest(String arg0) {
        super(arg0);
    }

    public void testSetsAndReps1() {
        ExerciseSets setsAndReps;

        System.out.println("\nStarting testSetsAndReps");

        setsAndReps = new ExerciseSets(3, 10);
        assertNotNull(setsAndReps);
        assertTrue(setsAndReps.getSets() == 3);
        assertTrue(setsAndReps.getReps() == 10);

        System.out.println("Finished testSetsAndReps");
    }
}
