package ledge.muscleup.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.w3c.dom.Text;

import java.util.List;

import ledge.muscleup.R;
import ledge.muscleup.business.AccessExperience;
import ledge.muscleup.business.AccessWorkoutSessions;
import ledge.muscleup.business.InterfaceAccessWorkoutSessions;
import ledge.muscleup.business.LevelCalculator;
import ledge.muscleup.model.exercise.WorkoutSessionExercise;
import ledge.muscleup.model.workout.WorkoutSession;

/**
 * CompletedWorkoutActivity displays the details of the experience gained from a completed workout,
 * including a list of exercises completed and their xp values, as well as the total xp gained,
 * the current level, and the amount of xp needed for the next level
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-27
 */
public class CompletedWorkoutActivity extends Activity {

    private static final DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy");
    private ListItemAdapter adapter;
    private WorkoutSession workoutSession;  //the workout session in view
    private AccessExperience accessExperience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_workout);

        accessExperience = new AccessExperience();
        ListView listView = (ListView) findViewById(R.id.completedExerciseList);

        getWorkoutSessionInView();

        TextView completedWorkoutNameTextView = (TextView) findViewById(R.id.completedSessionName);
        completedWorkoutNameTextView.setText(workoutSession.getName());

        TextView completedWorkoutTotalXP = (TextView) findViewById(R.id.completedWorkoutTotalXP);
        completedWorkoutTotalXP.setText("Total Experience Gained:  +" + String.valueOf(workoutSession.getExperienceValue() + " XP"));


        adapter = new ListItemAdapter(getApplicationContext(), R.layout.list_item_completed_exercise, workoutSession.getWorkoutSessionExercises());
        listView.setAdapter(adapter);

        Button backToScheduleButton = (Button) findViewById(R.id.backToScheduleButton);
        backToScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompletedWorkoutActivity.this, ScheduleActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Override the onBackPressed() method to jump to the ScheduleActivity whenever pressed from here
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CompletedWorkoutActivity.this, ScheduleActivity.class));
    }

    /**
     * Gets the completed WorkoutSession to view in this activity and show experience gained from it
     * @return the completed WorkoutSession to be used in the view
     */
    private WorkoutSession getWorkoutSessionInView() {
        LocalDate workoutSessionDate;
        Intent intent;
        InterfaceAccessWorkoutSessions aws = (InterfaceAccessWorkoutSessions) new AccessWorkoutSessions();

        //get workout session date
        intent = getIntent();
        workoutSessionDate = formatter.parseLocalDate(intent.getStringExtra("workoutSessionDate"));
        workoutSession = (WorkoutSession) aws.getWorkoutSession(workoutSessionDate);
        return workoutSession;
    }

    /**
     * A custom extension of the ArrayAdapter class, used for displaying completed exercises in the
     * workout session, with name and quantity completed, as well as xp gained for completing it
     */
    private class ListItemAdapter extends ArrayAdapter {
        private List<WorkoutSessionExercise> exerciseList;
        Context context;

        /**
         * A constructor for a ListItemAdapter, which is a custom ArrayAdapter used for displaying
         * exercise name and quantity along with the amount of xp gained from it
         * @param context The activity's context
         * @param resourceId the layout resource used for the adapter
         * @param exerciseList a list of exercises in the workout session
         */
        public ListItemAdapter(Context context, int resourceId, List<WorkoutSessionExercise> exerciseList) {
            super(context, resourceId, exerciseList);
            this.exerciseList = exerciseList;
            this.context = context;
        }

        /**
         * A wrapper class holding the different elements of a single list item in the checklist view
         */
        private class ViewHolder {
            TextView exerciseName;
            TextView exerciseQuantity;
            TextView exerciseXP;
        }


        /**
         * Returns a view containing the workout session exercise name and quantity along with the amount
         * of xp gained from completing it
         * @param index the index of the exercise in the exercise list
         * @param convertView the view used for conversion
         * @param parent the parent ViewGroup
         * @return a view containing the workout session exercise name and quantity along with the amount
         * of xp gained from completing it
         */
        @Override
        public View getView(final int index, View convertView, @NonNull ViewGroup parent) {
            ViewHolder viewHolder;

            final View returnedView;

            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_completed_exercise, parent, false);
                viewHolder.exerciseName = (TextView) convertView.findViewById(R.id.completedExerciseName);
                viewHolder.exerciseQuantity = (TextView) convertView.findViewById(R.id.completedExerciseQuantity);
                viewHolder.exerciseXP = (TextView) convertView.findViewById(R.id.completedExerciseXP);

                returnedView = convertView;
                convertView.setTag(viewHolder);
            } else {
                viewHolder= (ViewHolder) convertView.getTag();
                returnedView = convertView;
            }

            WorkoutSessionExercise sessionExercise = exerciseList.get(index);

            viewHolder.exerciseName.setText(sessionExercise.getName());
            viewHolder.exerciseQuantity.setText(sessionExercise.getRecommendedQuantity().toString());
            viewHolder.exerciseXP.setText("+" + String.valueOf(sessionExercise.getExperienceValue()) + " XP");
            return returnedView;
        }
    }
}
