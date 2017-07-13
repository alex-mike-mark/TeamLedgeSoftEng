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
        solo.clickOnButton("Workout Schedule");
        solo.assertCurrentActivity("We aren't in the schedule activity!",  ScheduleActivity.class);
        //a doozy.
        //bascally, need to check
        //move from week to week
        //add a workout
        //complete a workout
        //remove a workout
        solo.goBack();
    }
}
