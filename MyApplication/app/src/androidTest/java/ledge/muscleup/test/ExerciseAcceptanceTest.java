package ledge.muscleup.test;

/**
 * Created by Alexander on 2017-07-08.
 */

import ledge.muscleup.business.AccessExercises;
import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.presentation.ExerciseActivity;
import ledge.muscleup.presentation.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;


public class ExerciseAcceptanceTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;
    private AccessExercises ae;
    List<Exercise> list;
    public ExerciseAcceptanceTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation());
        getActivity();

        ae = new AccessExercises();
        list = ae.getExercisesList();

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
        solo.clickOnButton("Exercises");
        solo.assertCurrentActivity("We aren't in the exercise activity!", ExerciseActivity.class);
        for(int i = 0;i < list.size();i++){
            String exName = list.get(i).getName();
            String error = "Can't find exercise " + exName;
            assertTrue(error, solo.searchText(exName));
        }
        solo.goBack();
    }
}
