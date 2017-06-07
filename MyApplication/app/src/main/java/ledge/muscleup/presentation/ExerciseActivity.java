package ledge.muscleup.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ledge.muscleup.R;
import ledge.muscleup.application.Services;
import ledge.muscleup.persistence.DataAccessStub;

/**
 * ExerciseActivity displays a list of exercises, showing their name, intensity, and exercise type
 *
 * @author Jon Ingram
 * @version 1.0
 * @since 2017-06-04
 */

public class ExerciseActivity extends Activity {

    /**
     *  onCreate initializes ExerciseActivity
     * @param savedInstanceState contains context from last activity (eg MainActivity)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DataAccessStub db = Services.getDataAccess();
        List exerciseArray;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_display);

        exerciseArray = db.getExercisesList();

        TextView filter = (TextView) findViewById(R.id.filter_title);
        filter.setText("Filter: none");

        populateList(exerciseArray);

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
