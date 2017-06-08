package ledge.muscleup.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ledge.muscleup.R;
import ledge.muscleup.application.Services;

public class MainActivity extends AppCompatActivity {
    private static final String dbName = "workout_till_you_dropout";

    /**
     * Setup for MainActivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startUp();
    }

    /**
     * opens the WorkoutActivity
     * @param view
     */
    public void openWorkouts(View view) {
        Intent intent = new Intent(this, WorkoutActivity.class);
        startActivity(intent);
    }

    /**
     * opens the  ExerciseActivity
     * @param view
     */
    public void openExercises(View view) {
        Intent intent = new Intent(this, ExerciseActivity.class);
        startActivity(intent);
    }

    public void openSchedule(View view) {
        Intent intent = new Intent(this, ScheduleActivity.class);
        startActivity(intent);
    }

    /**
     * Create the stub stub database
     */
    private static void startUp()
    {
        Services.createDataAccess(dbName);
    }

    /**
     * Closes stub database connection
     */
    public static void shutDown()
    {
        Services.closeDataAccess();
    }
}
