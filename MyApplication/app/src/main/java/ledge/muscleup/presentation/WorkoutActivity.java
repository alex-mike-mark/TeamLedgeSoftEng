package ledge.muscleup.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ledge.muscleup.R;
import ledge.muscleup.business.AccessWorkouts;
import ledge.muscleup.business.InterfaceAccessWorkouts;
import ledge.muscleup.model.workout.Workout;

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
        InterfaceAccessWorkouts aw = new AccessWorkouts();
        final List<Workout> workoutList;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_display);

        ListView listView = (ListView) findViewById(R.id.list_panel);
        workoutList = aw.getWorkoutsList();

        TextView title = (TextView) findViewById(R.id.activity_title);
        title.setText(R.string.text_workoutCollection);

        ListItemAdapter adapter = new ListItemAdapter(getApplicationContext(), R.layout.list_item_workout, workoutList);
        listView.setAdapter(adapter);
        listView.setItemsCanFocus(true);

        setupListeners(workoutList);
    }

    /**
     * Places listeners on each list item so clicking will open WorkoutDetailsActivity
     * @param workoutArray
     */
    private void setupListeners(final List workoutArray) {
        ListView list = (ListView) findViewById(R.id.list_panel);

        //will respond to clicks by opening a WorkoutDetails and passing the name of the workout clicked
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent appInfo = new Intent(WorkoutActivity.this, WorkoutDetailsActivity.class);
                Workout workout =  (Workout) workoutArray.get(position);
                appInfo.putExtra("workoutName", workout.getName());
                startActivity(appInfo);
            }
        });
    }

    /**
     * A custom extension of the ArrayAdapter class, used for displaying workout name
     */
    private class ListItemAdapter extends ArrayAdapter {
        private List<Workout> workoutList;
        Context context;

        /**
         * A constructor for a ListItemAdapter, which is a custom ArrayAdapter used for displaying
         * workout name
         * @param context The activity's context
         * @param resourceId the layout resource used for the adapter
         * @param workoutList a list of workouts
         */
        public ListItemAdapter(Context context, int resourceId, List<Workout> workoutList) {
            super(context, resourceId, workoutList);
            this.workoutList = workoutList;
            this.context = context;
        }

        /**
         * A wrapper class holding the workoutName TextView
         */
        private class ViewHolder {
            TextView workoutName;
        }


        /**
         * Returns a view containing the workout name
         * @param index the index of the workout in the workout list
         * @param convertView the view used for conversion
         * @param parent the parent ViewGroup
         * @return a view containing the workout name
         */
        @Override
        public View getView(final int index, View convertView, @NonNull ViewGroup parent) {
            ViewHolder viewHolder;

            final View returnedView;

            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_workout, parent, false);
                viewHolder.workoutName = (TextView) convertView.findViewById(R.id.workoutName);

                returnedView = convertView;
                convertView.setTag(viewHolder);
            } else {
                viewHolder= (ViewHolder) convertView.getTag();
                returnedView = convertView;
            }

            Workout workout = workoutList.get(index);

            viewHolder.workoutName.setText(workout.getName());

            return returnedView;
        }
    }

}
