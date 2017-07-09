package ledge.muscleup.test;

/**
 * Created by Alexander on 2017-07-08.
 */

import ledge.muscleup.presentation.ExerciseActivity;
import ledge.muscleup.presentation.MainActivity;
import ledge.muscleup.presentation.ScheduleActivity;

import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;


public class WorkoutScheduleAcceptanceTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;

    public WorkoutScheduleAcceptanceTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation());
        getActivity();
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    @Test
    public void testRun() {
        solo.clickOnButton("Exercises");
        solo.assertCurrentActivity("We aren't in the schedule activity!",  ScheduleActivity.class);
        //ensure it is displaying things.
        solo.goBack();
    }
}
