package ledge.muscleup.test;

import ledge.muscleup.presentation.MainActivity;
import ledge.muscleup.presentation.WorkoutActivity;
import ledge.muscleup.presentation.WorkoutDetailsActivity;

import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;


public class WorkoutAcceptanceTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;

    public WorkoutAcceptanceTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation());
        getActivity();

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
