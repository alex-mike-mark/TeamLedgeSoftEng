package ledge.muscleup.test;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import org.joda.time.LocalDate;
import org.junit.Test;

import ledge.muscleup.business.AccessWorkoutSessions;
import ledge.muscleup.business.AccessWorkouts;
import ledge.muscleup.business.InterfaceAccessWorkoutSessions;
import ledge.muscleup.business.InterfaceAccessWorkouts;
import ledge.muscleup.model.workout.WorkoutSession;
import ledge.muscleup.presentation.MainActivity;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * Created by Jon on 2017-07-17.
 */

public class TodayAcceptanceTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;
    private InterfaceAccessWorkouts dataAccess;

    public TodayAcceptanceTest() {
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
    public void testTodayWorkout(){
        LocalDate date = new LocalDate();
        InterfaceAccessWorkoutSessions aws = new AccessWorkoutSessions();
        WorkoutSession currentDaySession = aws.getWorkoutSession(LocalDate.now());
        assertTrue("Today should be empty", currentDaySession == null);
        assertTrue("There should be no workout scheduled", solo.searchText("None Scheduled"));

        solo.clickOnButton("Workout Schedule");
        solo.clickOnButton(date.getDayOfWeek());
        solo.clickInList(3);
        solo.goBack();

        currentDaySession = aws.getWorkoutSession(LocalDate.now());
        assertFalse("There should be a workout scheduled", solo.searchText("None Scheduled"));

        solo.clickOnButton(0); //today's scheduled workout
        assertTrue("Today's workout not showing", solo.searchText(currentDaySession.getName()));
    }
}
