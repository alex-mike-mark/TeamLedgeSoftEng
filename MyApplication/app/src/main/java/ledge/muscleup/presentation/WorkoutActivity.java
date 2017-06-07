package ledge.muscleup.presentation;

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

import ledge.muscleup.R;
import ledge.muscleup.application.Services;
import ledge.muscleup.model.workout.Workout;
import ledge.muscleup.persistence.DataAccess;
import ledge.muscleup.persistence.DataAccessStub;

/**
 * WorkoutActivity displays a list of workouts that the user can click on to view list of exercises
 *
 * @author Jon Ingram
 * @version 1.0
 * @since 2017-06-04
 */

public class WorkoutActivity extends Activity {

    /**
     *  onCreate initializes WorkoutActivity
     * @param savedInstanceState contains context from last activity (eg MainActivity)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DataAccessStub db = Services.getDataAccess();
        List workoutArray;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        db.open("workouts");
        workoutArray = db.getWorkoutNamesList();

        TextView filter = (TextView) findViewById(R.id.filter_title);
        filter.setText("Filter: none");

        populateList(workoutArray);

        //this will be used to implement Workout Details
//        ListView listView = (ListView) findViewById(R.id.workout_list);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                if (position == 0) {
//
//                }
//
//                if (position == 1) {
//
//                    }
//                }
//            }
//        });
    }

    /**
     *  Used to insert a List into a list pane defined in the xml as "workout_list"
     * @param arrayList
     */
    private void populateList(List arrayList) {
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, arrayList);

        ListView listView = (ListView) findViewById(R.id.workout_list);
        listView.setAdapter(adapter);
    }
}
