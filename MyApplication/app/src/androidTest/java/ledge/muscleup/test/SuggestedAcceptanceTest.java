package ledge.muscleup.test;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

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
    public void testSuggestedWorkout() {
        String suggestedWorkoutName;
        InterfaceAccessWorkouts dataAccess = new AccessWorkouts();

        suggestedWorkoutName = dataAccess.getSuggestedWorkout();

        assertTrue("Suggested workout is wrong", solo.searchText(suggestedWorkoutName));
    }

}
