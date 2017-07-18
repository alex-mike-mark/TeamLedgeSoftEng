package ledge.muscleup.test;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import org.junit.Test;

import ledge.muscleup.business.AccessWorkouts;
import ledge.muscleup.business.InterfaceAccessWorkouts;
import ledge.muscleup.persistence.InterfaceWorkoutDataAccess;
import ledge.muscleup.presentation.MainActivity;
import ledge.muscleup.presentation.WorkoutActivity;
import ledge.muscleup.presentation.WorkoutDetailsActivity;

/**
 * Created by Jon on 2017-07-17.
 */

public class SuggestedAcceptanceTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;
    private InterfaceAccessWorkouts dataAccess;

    public SuggestedAcceptanceTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation());
        getActivity();

        dataAccess = new AccessWorkouts();
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }


    @Test
    public void testSuggestedWorkout() {
        String suggestedWorkoutName;
        InterfaceAccessWorkouts dataAccess = new AccessWorkouts();

        suggestedWorkoutName = dataAccess.getSuggestedWorkout();

        assertTrue("Suggested workout is wrong", solo.searchText(suggestedWorkoutName));
    }

}
