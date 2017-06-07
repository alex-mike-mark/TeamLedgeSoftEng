package ledge.muscleup.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import ledge.muscleup.R;
import ledge.muscleup.application.Services;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.workout.Workout;
import ledge.muscleup.persistence.InterfaceDataAccessStub;

/**
 * WorkoutDetailsActivity displays a list of exercises for a workout
 *
 * @author Jon Ingram
 * @version 1.0
 * @since 2017-06-04
 */

public class WorkoutDetailsActivity extends Activity {

    /**
     *  onCreate initializes WorkoutDetailsActivity
     * @param savedInstanceState contains context from last activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String workoutName;
        Intent intent;
        Workout workout;
        InterfaceDataAccessStub db = Services.getDataAccess();
        List exerciseList = new ArrayList();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_display);

        //get name of workout
        intent = getIntent();
        workoutName = intent.getStringExtra("workoutName");

        //get Workout from db
        workout = (Workout) db.getWorkout(workoutName);

        //fetch all exercises from workout
        Enumeration<WorkoutExercise> exercises = workout.getExerciseEnumeration();
        while(exercises.hasMoreElements()){
            exerciseList.add(exercises.nextElement());
        }

        TextView filter = (TextView) findViewById(R.id.filter_title);
        filter.setText("Filter: none");

        populateList(exerciseList);
    }

    /**
     *  Used to insert a List into a list pane defined in the xml as "list_panel"
     * @param arrayList
     */
    private void populateList(List arrayList) {
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, arrayList);

        ListView listView = (ListView) findViewById(R.id.list_panel);
        listView.setAdapter(adapter);
    }
}
