package ledge.muscleup.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ledge.muscleup.R;
import ledge.muscleup.application.Services;
import ledge.muscleup.persistence.DataAccessStub;
import ledge.muscleup.persistence.InterfaceDataAccess;

public class ScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        InterfaceDataAccess db = Services.getDataAccess();
        List scheduleArray;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_display);

        scheduleArray = db.getWorkoutSessionsList();

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
