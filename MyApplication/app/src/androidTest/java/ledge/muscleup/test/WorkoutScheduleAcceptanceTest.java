package ledge.muscleup.test;

/**
 * Created by Alexander on 2017-07-08.
 */

import ledge.muscleup.*;
import ledge.muscleup.business.AccessWorkoutSessions;
import ledge.muscleup.model.schedule.ScheduleWeek;
import ledge.muscleup.model.workout.WorkoutSession;
import ledge.muscleup.presentation.ExerciseActivity;
import ledge.muscleup.presentation.MainActivity;
import ledge.muscleup.presentation.ScheduleActivity;

import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.util.List;


public class WorkoutScheduleAcceptanceTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;
    private AccessWorkoutSessions aws;
    private ScheduleWeek scheduleWeek;
    private List<WorkoutSession> sessionList;
    private int weekStartDay;

    private static final DateTimeFormatter monthDayYearFormatter = DateTimeFormat.forPattern("MM/dd/yyyy");

    public WorkoutScheduleAcceptanceTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation());
        getActivity();

        aws = new AccessWorkoutSessions();
        weekStartDay = DateTimeConstants.SUNDAY;
        scheduleWeek = new ScheduleWeek(weekStartDay, aws.getCurrentWeekSessions(weekStartDay));
        sessionList = scheduleWeek.getWorkoutSessionList();
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    @Test
    public void testWeek(){
        LocalDate startOfWeek;
        LocalDate endOfWeek;
        String currWeek;
        String nextWeek; //from current
        String prevWeek; //from current
        solo.clickOnButton("Workout Schedule");
        solo.assertCurrentActivity("We aren't in the schedule activity!",  ScheduleActivity.class);

        //get String for current week
        startOfWeek = sessionList.get(0).getDate();
        endOfWeek = sessionList.get(sessionList.size() - 1).getDate();
        currWeek = monthDayYearFormatter.print(startOfWeek) + " - " + monthDayYearFormatter.print(endOfWeek);
        assertTrue("Wrong current week range", solo.searchText(currWeek));

        //get String for next week
        aws.setToNextWeek(scheduleWeek);
        sessionList = scheduleWeek.getWorkoutSessionList();
        startOfWeek = sessionList.get(0).getDate();
        endOfWeek = sessionList.get(sessionList.size() - 1).getDate();
        nextWeek = monthDayYearFormatter.print(startOfWeek) + " - " + monthDayYearFormatter.print(endOfWeek);
        assertFalse("Next week should not be same as current", solo.searchText(nextWeek));
        solo.clickOnButton("Next");
        assertTrue("Wrong next week range", solo.searchText(nextWeek));

        solo.clickOnButton("Current Week");
        assertTrue("Current week is wrong", solo.searchText(currWeek));

        //get String for previous week
        aws.setToLastWeek(scheduleWeek); //because we are now curr week + 1, so go back twice for previous
        aws.setToLastWeek(scheduleWeek);
        sessionList = scheduleWeek.getWorkoutSessionList();
        startOfWeek = sessionList.get(0).getDate();
        endOfWeek = sessionList.get(sessionList.size() - 1).getDate();
        prevWeek = monthDayYearFormatter.print(startOfWeek) + " - " + monthDayYearFormatter.print(endOfWeek);
        solo.clickOnButton("Previous");
        assertTrue("Previous week is wrong", solo.searchText(prevWeek));

        solo.clickOnButton("Current Week");
        assertTrue("Current week is wrong", solo.searchText(currWeek));

        solo.goBack();
    }

    @Test
    public void testAddScheduledWorkout(){
        String workoutName = "Never Skip Leg Day";
        solo.clickOnButton("Workout Schedule");
        solo.clickOnButton("+");
        solo.clickOnText(workoutName);

        assertTrue("Workout not added", solo.searchText(workoutName));
        solo.goBack();
    }

    @Test
    public void testRun() {
        solo.clickOnButton("Workout Schedule");
        solo.assertCurrentActivity("We aren't in the schedule activity!",  ScheduleActivity.class);

        //add a workout
        //complete a workout
        //remove a workout
        solo.goBack();
    }
}
