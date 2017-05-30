package ledge.muscleup.model;

/**
 * Created by koope on 2017-05-29.
 */

import junit.framework.TestCase;
import ledge.muscleup.model.ExerciseSetsAndReps;

public class ExerciseSetsAndRepsTest extends TestCase{

    public ExerciseSetsAndRepsTest(String arg0) {
        super(arg0);
    }

    public void testSetsAndReps1() {
        ExerciseSetsAndReps setsAndReps;

        System.out.println("\nStarting testSetsAndReps");

        setsAndReps = new ExerciseSetsAndReps(3, 10);
        assertNotNull(setsAndReps);
        assertTrue(setsAndReps.getSets() == 3);
        assertTrue(setsAndReps.getReps() == 10);

        System.out.println("Finished testSetsAndReps");
    }
}
