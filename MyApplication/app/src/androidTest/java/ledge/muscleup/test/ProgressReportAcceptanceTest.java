package ledge.muscleup.test;

/**
 * Created by Alexander on 2017-07-08.
 */

import ledge.muscleup.presentation.ExerciseActivity;
import ledge.muscleup.presentation.MainActivity;
import ledge.muscleup.presentation.ProgressReportActivity;

import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;


public class ProgressReportAcceptanceTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;
    private LocalDate today;
    private static final DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd");

    public ProgressReportAcceptanceTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation());
        getActivity();

        today = new LocalDate();
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    @Test
    public void testRun() {

        //check that progress report exists, we start at level 0.
        solo.clickOnButton("Progress Report");
        solo.assertCurrentActivity("We aren't in the prog report activity!", ProgressReportActivity.class);
        assertTrue("We ain't level 0",solo.searchText("LEVEL 0"));
        solo.goBack();

        //complete some workouts to boost our level.
        solo.clickOnButton("Workout Schedule");
        solo.clickOnButton(0);
        solo.clickInList(0);
        solo.clickOnButton(1);
        solo.clickInList(1);
        solo.clickOnButton(2);
        solo.clickInList(2);
        solo.clickOnButton(3);
        solo.clickInList(3);

        //use clickOnText(date) extensively.
        //or rather, getButton? Get ListView?
        //date is obv formatted.


        //ensure it is displaying things.
        //go make and complete some workouts
        //return to this screen, check stats.
        solo.goBack();
    }
}
