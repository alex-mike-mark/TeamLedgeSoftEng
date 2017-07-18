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
import ledge.muscleup.presentation.WorkoutSessionActivity;

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
    public void testScheduling() {
        solo.clickOnButton("Workout Schedule");
        solo.assertCurrentActivity("We aren't in the schedule activity!",  ScheduleActivity.class);

        //schedule and remove some things.
        solo.clickOnButton(0);
        solo.clickInList(0);
        solo.clickOnButton(1);
        solo.clickInList(1);
        solo.clickOnButton(2);
        solo.clickInList(2);
        solo.clickOnButton(3);
        solo.clickInList(3);
        solo.clickOnButton(4);
        solo.clickInList(4);

        assertTrue("Can't find added workout",solo.searchText("Welcome to the Gun Show"));
        assertTrue("Can't find added workout",solo.searchText("Marathon Training Starts Here"));
        assertTrue("Can't find added workout",solo.searchText("Work that Core, Get that Score!"));
        assertTrue("Can't find added workout",solo.searchText("Never Skip Leg Day"));

        solo.clickOnButton(4);


        //Go to complete a workout.
        solo.clickInList(0);
        solo.assertCurrentActivity("we are't in the workout screen.", WorkoutSessionActivity.class);
        solo.clickOnButton("Complete");
        solo.clickOnButton("Back");

        //Attempt to complete a workout you are not allowed to complete.
        solo.assertCurrentActivity("We aren't in the schedule activity", ScheduleActivity.class);
        solo.clickOnButton("Next");
        solo.clickOnButton(3);
        solo.clickInList(1);
        solo.clickInList(4);
        solo.goBack();
    }
}
