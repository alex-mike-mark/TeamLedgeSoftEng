package ledge.muscleup;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ledge.muscleup.model.workout.Workout;
import ledge.muscleup.persistence.DataAccess;
import ledge.muscleup.persistence.DataAccessStub;

/**
 * WorkoutActivity displays a list of workouts
 *
 * @author Jon Ingram
 * @version 1.0
 * @since 2017-06-04
 */

public class WorkoutActivity extends Activity {
    DataAccessStub db = new DataAccessStub("workouts");
    List workoutArray;
    Workout workout;

    /**
     *  onCreate initializes WorkoutActivity
     * @param savedInstanceState contains context from last activity (eg MainActivity)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        db.open("workouts");
        workoutArray = db.getWorkoutsList();

        ListView listView = (ListView) findViewById(R.id.workout_list);

        TextView filter = (TextView) findViewById(R.id.filter_title);
        filter.setText("Filter: none");

        populateList(workoutArray);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {
                    workout = (Workout) workoutArray.get(0);
                    workout.initExerciseIteration();
                    TextView filter = (TextView) findViewById(R.id.filter_title);
                    filter.setText(workout.toString());
                }

                if (position == 1) {
                    workout = (Workout) workoutArray.get(1);
                    workout.initExerciseIteration();
                    if(workout.hasNextExercise()) {
                        TextView filter = (TextView) findViewById(R.id.filter_title);
                        filter.setText(workout.nextExercise().getName());
                    }
                }
            }
        });
    }

    private void populateList(List arrayList) {
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, arrayList);

        ListView listView = (ListView) findViewById(R.id.workout_list);
        listView.setAdapter(adapter);
    }
}
