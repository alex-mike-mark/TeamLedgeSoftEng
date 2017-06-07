package ledge.muscleup.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ledge.muscleup.R;
import ledge.muscleup.application.Services;
import ledge.muscleup.persistence.DataAccessStub;

/**
 * Created by Alexander on 2017-06-07.
 */

public class ScheduleActivity extends AppCompatActivity{
    /**
     *  onCreate initializes ExerciseActivity
     * @param savedInstanceState contains context from last activity (eg MainActivity)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DataAccessStub db = Services.getDataAccess();
        List scheduleArray;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_display);

        scheduleArray = db.getWorkoutSessionsList();

        TextView filter = (TextView) findViewById(R.id.filter_title);
        filter.setText("Filter: none");

        populateList(scheduleArray);

    }

    /**
     *  Used to insert a List into a list pane defined in the xml as "workout_list"
     * @param arrayList
     */
    private void populateList(List arrayList) {
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, arrayList);

        ListView listView = (ListView) findViewById(R.id.list_panel);
        listView.setAdapter(adapter);
    }
}
