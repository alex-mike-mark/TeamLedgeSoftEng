package ledge.muscleup.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import ledge.muscleup.R;
import ledge.muscleup.application.Main;
import ledge.muscleup.business.AccessWorkoutSessions;
import ledge.muscleup.business.AccessWorkouts;
import ledge.muscleup.business.InterfaceAccessWorkoutSessions;
import ledge.muscleup.business.InterfaceAccessWorkouts;
import ledge.muscleup.model.workout.WorkoutSession;

public class MainActivity extends Activity {
    public static final String dbName="workout_till_you_dropout";

    /**
     * Setup for MainActivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        copyDatabaseToDevice();

        setContentView(R.layout.activity_main);

        Main.startUp();

        InterfaceAccessWorkoutSessions aws = new AccessWorkoutSessions();
        final WorkoutSession currentDaySession = aws.getWorkoutSession(LocalDate.now());

        Button currentDayWorkoutButton = (Button)  findViewById(R.id.btn_currentDayWorkoutSession);
        if (currentDaySession == null) {
            currentDayWorkoutButton.setText("Today's Scheduled Workout:" + System.getProperty("line.separator") + "None Scheduled");
            currentDayWorkoutButton.setClickable(false);
            currentDayWorkoutButton.setAlpha(0.5f);
        } else {
            currentDayWorkoutButton.setText("Today's Scheduled Workout:"  + System.getProperty("line.separator") + currentDaySession.getName());
            currentDayWorkoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent workoutSessionIntent = new Intent(MainActivity.this, WorkoutSessionActivity.class);
                    LocalDate date = currentDaySession.getDate();
                    DateTimeFormatter formatter= DateTimeFormat.forPattern("MM/dd/yyyy");

                    workoutSessionIntent.putExtra("workoutSessionDate", formatter.print(date));
                    startActivity(workoutSessionIntent);
                }
            });
        }

        InterfaceAccessWorkouts aw = new AccessWorkouts();
        Button suggestedWorkoutButton = (Button) findViewById(R.id.btn_suggestedWorkout);
        final String suggestedWorkoutName = aw.getSuggestedWorkout();
        suggestedWorkoutButton.setText("Today's Suggested Workout: " + System.getProperty("line.separator") + suggestedWorkoutName);
        suggestedWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent suggestedWorkoutIntent = new Intent(MainActivity.this, WorkoutDetailsActivity.class);
                suggestedWorkoutIntent.putExtra("workoutName", suggestedWorkoutName);
                startActivity(suggestedWorkoutIntent);
            }
        });
    }

    /**
     * Overrides the onBackPressed() method to exit the application when back is pressed while on the Main activity
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * Cleans up the application when destroyed
     */
    protected void onDestroy() {
        super.onDestroy();

        Main.shutDown();
    }

    /**
     * Opens the WorkoutActivity
     * @param view
     */
    public void openWorkouts(View view) {
        Intent intent = new Intent(this, WorkoutActivity.class);
        startActivity(intent);
    }

    /**
     * Opens the ExerciseActivity
     * @param view
     */
    public void openExercises(View view) {
        Intent intent = new Intent(this, ExerciseActivity.class);
        startActivity(intent);
    }

    /**
     * Opens the ScheduleActivity
     * @param view
     */
    public void openSchedule(View view) {
        Intent intent = new Intent(this, ScheduleActivity.class);
        startActivity(intent);
    }

    /**
     * Opens the ProgressReportActivity
     * @param view
     */
    public void openProgressReport(View view) {
        Intent intent = new Intent (this, ProgressReportActivity.class);
        startActivity(intent);
    }

    /**
     * Copies all database files to the device
     */
    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.dbName);

        } catch (IOException ioe) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();

            alertDialog.setTitle(this.getString(R.string.warning));
            alertDialog.setMessage("Unable to access application data: " + ioe.getMessage());

            alertDialog.show();
        }
    }

    /**
     * Copies all assets files to a given directory, provided they don't already exists
     * @param assets the files in the assets folder
     * @param directory the directory to copy to
     * @throws IOException when an error occuring while reading or writing
     */
    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];
            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            //if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            //}
        }
    }
}
