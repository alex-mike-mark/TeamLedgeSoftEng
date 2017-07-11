package ledge.muscleup.test;

/**
 * Created by Alexander on 2017-07-08.
 */

import ledge.muscleup.presentation.ExerciseActivity;
import ledge.muscleup.presentation.MainActivity;
import ledge.muscleup.presentation.ProgressReportActivity;

import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;


public class ProgressReportAcceptanceTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;

    public ProgressReportAcceptanceTest() {
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
        solo.assertCurrentActivity("We aren't in the prog report activity!", ProgressReportActivity.class);
        //ensure it is displaying things.
        solo.goBack();
    }
}