package ledge.muscleup.test;

import ledge.muscleup.presentation.MainActivity;
import ledge.muscleup.presentation.WorkoutActivity;
import ledge.muscleup.presentation.WorkoutDetailsActivity;

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
    public void testWorkoutsActivity() {
        solo.clickOnButton("Workouts");
        solo.assertCurrentActivity("We aren't in the workout activity!", WorkoutActivity.class);
        //click on a workout to get some info.
        assertTrue(solo.searchText("Welcome to the Gun Show"));//there is probably a better way to do this. Maybe db access and get a random one?
        assertTrue(solo.searchText("Never Skip Leg Day"));
        assertTrue(solo.searchText("Marathon Training Starts Here"));
        assertTrue(solo.searchText("Work that Core, Get that Score!"));
        solo.clickInList(1);
        solo.assertCurrentActivity("We aren't in workout deets!", WorkoutDetailsActivity.class);
        solo.goBack();
        solo.assertCurrentActivity("We aren't in the workout activity!", WorkoutActivity.class);
        solo.goBack();
        solo.assertCurrentActivity("We aren't in the main activity!", MainActivity.class);

    }
}
