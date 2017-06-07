package ledge.muscleup.model;

/**
 * Created by koope on 2017-05-29.
 */
import junit.framework.TestCase;

import ledge.muscleup.model.exercise.ExerciseDuration;
public class ExerciseDurationTest extends TestCase {

    public ExerciseDurationTest (String arg0) {
        super (arg0);
    }

    public void testExerciseDuration1() {
        ExerciseDuration exerciseDuration;

        System.out.println("Starting testExerciseDuration");

        exerciseDuration = new ExerciseDuration(45);
        assertNotNull(exerciseDuration);
        assertTrue(exerciseDuration.getMinutes() == 45);

        System.out.println("Finished testExerciseDuration");
    }
}
