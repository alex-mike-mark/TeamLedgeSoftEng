package ledge.muscleup.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ledge.muscleup.R;
import ledge.muscleup.application.Services;
import ledge.muscleup.business.AccessExercises;
import ledge.muscleup.business.InterfaceAccessExercises;
import ledge.muscleup.persistence.InterfaceDataAccess;

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
        InterfaceAccessExercises ae = new AccessExercises();
        ListManager lm = new ListManager();
        List exerciseArray;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_display);

        exerciseArray = ae.getExercisesList();

        TextView title = (TextView) findViewById(R.id.activity_title);
        title.setText("Exercise Collection");

        TextView filter = (TextView) findViewById(R.id.filter_title);
        filter.setText("Filter: none");

        lm.populateList(this, exerciseArray);
    }

}
