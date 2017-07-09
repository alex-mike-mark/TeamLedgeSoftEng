package ledge.muscleup.test;

import ledge.muscleup.presentation.MainActivity;
import ledge.muscleup.presentation.WorkoutActivity;

import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;


public class WorkoutAcceptanceTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;

    public WorkoutAcceptanceTest() {
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
        solo.clickOnButton("Workouts");
        solo.assertCurrentActivity("We aren't in the workout activity!", WorkoutActivity.class);
        //ensure it is displaying things.
        solo.goBack();
    }
}
