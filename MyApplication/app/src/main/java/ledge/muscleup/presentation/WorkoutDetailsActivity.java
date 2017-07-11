package ledge.muscleup.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ledge.muscleup.R;
import ledge.muscleup.business.AccessWorkouts;
import ledge.muscleup.business.InterfaceAccessWorkouts;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.workout.Workout;

/**
 * WorkoutDetailsActivity displays a list of exercises for a workout
 *
 * @author Jon Ingram
 * @version 1.0
 * @since 2017-06-04
 */

public class WorkoutDetailsActivity extends Activity {
    private ListItemAdapter adapter;
    private Workout workout;

    /**
     *  onCreate initializes WorkoutDetailsActivity
     * @param savedInstanceState contains context from last activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        List<WorkoutExercise> exerciseList;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_details);

        exerciseList = getExerciseList();

        ListView listView = (ListView) findViewById(R.id.list_panel);

        adapter = new ListItemAdapter(getApplicationContext(), R.layout.list_item_workout_exercise, exerciseList);
        TextView title = (TextView) findViewById(R.id.activity_title);
        title.setText(workout.getName());

        listView.setAdapter(adapter);
        listView.setItemsCanFocus(true);

        Button goToSchedule = (Button) findViewById(R.id.goToSchedule);
        goToSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WorkoutDetailsActivity.this, ScheduleActivity.class));
            }
        });

    }

    /**
     * getExerciseList will get the workout that was clicked on in WorkoutActivity and return the exercises associated
     * @return list of exercises for a workout
     */
    private List getExerciseList(){
        String workoutName;
        Intent intent;
        InterfaceAccessWorkouts aw = (InterfaceAccessWorkouts) new AccessWorkouts();
        List retList = new ArrayList();

        //get name of workout
        intent = getIntent();
        workoutName = intent.getStringExtra("workoutName");

        //get Workout from db
        workout = (Workout) aw.getWorkout(workoutName);

        return workout.getExerciseList();
    }

    private class ListItemAdapter extends ArrayAdapter {
        private List<WorkoutExercise> exerciseList;
        Context context;

        /**
         * A constructor for a ListItemAdapter, which is a custom ArrayAdapter used for displaying
         * exercise name and quantity
         * @param context The activity's context
         * @param resourceId the layout resource used for the adapter
         * @param exerciseList a list of exercises in the workout
         */
        public ListItemAdapter(Context context, int resourceId, List<WorkoutExercise> exerciseList) {
            super(context, resourceId, exerciseList);
            this.exerciseList = exerciseList;
            this.context = context;
        }

        /**
         * A wrapper class holding the different elements of a single list item in the list view.
         * Contains 1 TextView for exercise name, and 1 TextView that contians the quantity
         */
        private class ViewHolder {
            TextView exerciseName;
            TextView exerciseInfo;
        }


        /**
         * Returns a view containing the workout exercise name and quantity
         * @param index the index of the exercise in the exercise list
         * @param convertView the view used for conversion
         * @param parent the parent ViewGroup
         * @return a view containing the workout exercise name and quantity along with a
         * checkbox for each exercise in the workout session
         */
        @Override
        public View getView(final int index, View convertView, @NonNull ViewGroup parent) {ViewHolder viewHolder;

            final View returnedView;

            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_workout_exercise, parent, false);
                viewHolder.exerciseName = (TextView) convertView.findViewById(R.id.workoutExerciseName);
                viewHolder.exerciseInfo = (TextView) convertView.findViewById(R.id.workoutExerciseQuantity);

                returnedView = convertView;
                convertView.setTag(viewHolder);
            } else {
                viewHolder= (ViewHolder) convertView.getTag();
                returnedView = convertView;
            }

            WorkoutExercise exercise = exerciseList.get(index);

            viewHolder.exerciseName.setText(exercise.getName());
            String exerciseInfo = ExerciseQuantityDisplayStrings.getExerciseQuantityDisplayString(exercise.getQuantity());
            viewHolder.exerciseInfo.setText(exerciseInfo);

            return returnedView;
        }
    }
}
