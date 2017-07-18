package ledge.muscleup.test;

/**
 * Created by Alexander on 2017-07-08.
 */

import ledge.muscleup.business.AccessWorkoutSessions;
import ledge.muscleup.business.AccessWorkouts;
import ledge.muscleup.model.workout.Workout;
import ledge.muscleup.model.workout.WorkoutSession;
import ledge.muscleup.presentation.MainActivity;
import ledge.muscleup.presentation.ProgressReportActivity;
import ledge.muscleup.presentation.WorkoutSessionActivity;

import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;


public class ProgressReportAcceptanceTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;
    private LocalDate today;
    private static final DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd");
    private AccessWorkouts aw;
    private AccessWorkoutSessions aws;

    public ProgressReportAcceptanceTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation());
        getActivity();
        aw = new AccessWorkouts();
        aws = new AccessWorkoutSessions();

        today = new LocalDate();
        copy(new File("./app/MU_DB.script"), new File("./app/MU_DB_COPY.script"));
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
        copy(new File("./app/MU_DB_COPY.script"), new File("./app/MU_DB.script"));
    }

    private void copy(File sourceFile, File destinationFile) {
        FileInputStream inputStream;
        FileOutputStream outputStream;
        FileChannel inputChannel;
        FileChannel outputChannel;

        try {
            inputStream = new FileInputStream(sourceFile);
            outputStream = new FileOutputStream(destinationFile);

            inputChannel = inputStream.getChannel();
            outputChannel = outputStream.getChannel();
            inputChannel.transferTo(0, inputChannel.size(), outputChannel);

            inputStream.close();
            outputStream.close();
        }
        catch (IOException ioe) {
            System.out.printf("Error copying database file: " + ioe.getMessage());
        }
    }

    @Test
    public void testRun() {

        //a lot of set up to add some workout sessions into the schedule
        //rationale: figuring out how to do that without the recorder was eating up
        //a lot of time. Working through the db is much simpler.
        LocalDate oneAgo, twoAgo, threeAgo;
        WorkoutSession ws0,ws1,ws2,ws3;
        boolean onCurrWeek = false;

        oneAgo = today.minusDays(1);
        twoAgo = today.minusDays(2);
        threeAgo = today.minusDays(3);

        List<Workout> wList = aw.getWorkoutsList();

        //create and insert the workouts to be used to grind to level 1.
        ws0 = new WorkoutSession(wList.get(3),today,false);
        ws1 = new WorkoutSession(wList.get(2),oneAgo,false);
        ws2 = new WorkoutSession(wList.get(1),twoAgo,false);

        aws.insertWorkoutSession(ws0);
        aws.insertWorkoutSession(ws1);
        aws.insertWorkoutSession(ws2);


        //check that progress report exists, we start at level 0.
        solo.clickOnButton("Progress Report");
        solo.assertCurrentActivity("We aren't in the prog report activity!", ProgressReportActivity.class);
        assertTrue("We ain't level 0",solo.searchText("LEVEL 0"));
        solo.goBack();

        //complete some workouts to boost our level.
        //we go chronologically here to simplify flipping between weeks.
        solo.clickOnButton("Workout Schedule");
        solo.clickOnButton("Previous");

        for(int i = 0;i<3;i++){
            //if we can't find the workout we want to complete last week, all the rest are this week.
            if(!solo.searchText(wList.get(i).getName())){
                onCurrWeek = true;
                solo.clickOnButton("Next");
            }
            solo.clickOnText(wList.get(i).getName());
            solo.assertCurrentActivity("Somehow we aren't in the workout session activity", WorkoutSessionActivity.class);
            solo.clickOnText("Complete");
            solo.clickOnText("Back");

            //this activity defaults tot he current week, so
            //if we're still in last week, we have to go back again.
            if(!onCurrWeek){
                solo.clickOnButton("Previous");
            }
        }

        //double check on our progress report.
        solo.goBack();
        solo.clickOnButton("Progress Report");
        solo.assertCurrentActivity("We aren't in the prog report activity!", ProgressReportActivity.class);
        assertTrue("We ain't level 1",solo.searchText("LEVEL 1"));


    }
}
