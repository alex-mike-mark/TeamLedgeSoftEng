package ledge.muscleup.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ledge.muscleup.R;
import ledge.muscleup.application.Services;
import ledge.muscleup.persistence.InterfaceDataAccess;

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
        InterfaceDataAccess db = Services.getDataAccess();
        final List workoutArray;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_display);

        workoutArray = db.getWorkoutNamesList();

        TextView filter = (TextView) findViewById(R.id.filter_title);
        filter.setText("Filter: none");

        populateList(workoutArray);

        ListView list = (ListView) findViewById(R.id.list_panel);

        //will respond to clicks by opening a WorkoutDetails and passing the name of the workout clicked
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent appInfo = new Intent(WorkoutActivity.this, WorkoutDetailsActivity.class);
                appInfo.putExtra("workoutName", workoutArray.get(position).toString());
                startActivity(appInfo);
            }
        });
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
