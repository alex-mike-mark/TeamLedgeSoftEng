package ledge.muscleup.presentation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

import ledge.muscleup.R;
import ledge.muscleup.business.AccessExercises;
import ledge.muscleup.business.InterfaceAccessExercises;
import ledge.muscleup.model.exercise.Exercise;

/**
 * ExerciseActivity displays a list of exercises, showing their name, intensity, and exercise type
 *
 * @author Jon Ingram
 * @version 1.0
 * @since 2017-06-04
 */

public class ExerciseActivity extends Activity {
    private ListItemAdapter adapter;
    private static final DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy");
    /**
     *  onCreate initializes ExerciseActivity
     * @param savedInstanceState contains context from last activity (eg MainActivity)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        InterfaceAccessExercises ae = new AccessExercises();

        List exerciseList;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_display);

        ListView listView = (ListView) findViewById(R.id.list_panel);
        exerciseList = ae.getExercisesList();

        TextView title = (TextView) findViewById(R.id.activity_title);
        title.setText("Exercise Collection");

        adapter = new ListItemAdapter(getApplicationContext(), R.layout.list_item_exercise, exerciseList);
        listView.setAdapter(adapter);
        listView.setItemsCanFocus(true);
    }

    /**
     * A custom extension of the ArrayAdapter class, used for displaying exercise name as well
     * as exercise type and intensity
     */
    private class ListItemAdapter extends ArrayAdapter {
        private List<Exercise> exerciseList;
        Context context;

        /**
         * A constructor for a ListItemAdapter, which is a custom ArrayAdapter used for displaying
         * exercise name as well as type and intensity
         * @param context The activity's context
         * @param resourceId the layout resource used for the adapter
         * @param exerciseList a list of exercises
         */
        public ListItemAdapter(Context context, int resourceId, List<Exercise> exerciseList) {
            super(context, resourceId, exerciseList);
            this.exerciseList = exerciseList;
            this.context = context;
        }

        /**
         * A wrapper class holding the different elements of a single list item in the list view,
         * 1 TextView for the exerciseName and 1 for the rest of the info
         */
        private class ViewHolder {
            TextView exerciseName;
            TextView exerciseInfo;
        }


        /**
         * Returns a view containing the exercise name, intensity, and type
         * @param index the index of the exercise in the exercise list
         * @param convertView the view used for conversion
         * @param parent the parent ViewGroup
         * @return a view containing the exercise name, intensity, and type
         */
        @Override
        public View getView(final int index, View convertView, @NonNull ViewGroup parent) {
            ViewHolder viewHolder;

            final View returnedView;

            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_exercise, parent, false);
                viewHolder.exerciseName = (TextView) convertView.findViewById(R.id.exerciseName);
                viewHolder.exerciseInfo = (TextView) convertView.findViewById(R.id.exerciseInfo);

                returnedView = convertView;
                convertView.setTag(viewHolder);
            } else {
                viewHolder= (ViewHolder) convertView.getTag();
                returnedView = convertView;
            }

            Exercise exercise = exerciseList.get(index);

            viewHolder.exerciseName.setText(exercise.getName());
            String exerciseInfo = "Exercise Type: " + exercise.getType().toString() + " | Intensity: " + exercise.getIntensity().toString();
            viewHolder.exerciseInfo.setText(exerciseInfo);

            return returnedView;
        }
    }
}
