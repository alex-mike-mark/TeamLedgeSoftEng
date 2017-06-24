package ledge.muscleup.presentation;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import ledge.muscleup.R;
import ledge.muscleup.business.AccessWorkoutSessions;
import ledge.muscleup.business.InterfaceAccessWorkoutSessions;
import ledge.muscleup.model.exercise.WorkoutSessionExercise;
import ledge.muscleup.model.workout.WorkoutSession;

/**
 * WorkoutSessionActivity displays a checklist of exercises in a WorkoutSession with a button to
 * complete the workout once all of the exercises in the workout have been checked off as completed
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-23
 */
public class WorkoutSessionActivity extends AppCompatActivity {

    private WorkoutSession workoutSession;  //the workout session in view
    private CheckboxAdapter adapter;
    private int numCompletedExercises;      //number of exercises checked off as completed
    private static final DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy");

    /**
     *  onCreate initializes WorkoutSessionActivity
     * @param savedInstanceState contains context from last activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_session_checklist);

        ListView listView = (ListView) findViewById(R.id.checklist);
        final List<WorkoutSessionExercise> exerciseList = getExercisesInWorkoutSession();

        String dateString = formatter.print(workoutSession.getDate());
        TextView sessionDateTextView = (TextView) findViewById(R.id.workoutSessionDate);
        sessionDateTextView.setText(dateString);

        TextView sessionNameTextView = (TextView) findViewById(R.id.workoutSessionTitle);
        sessionNameTextView.setText(workoutSession.getName());

        numCompletedExercises = 0;

        adapter = new CheckboxAdapter(getApplicationContext(), R.layout.activity_workout_session_exercise, exerciseList);

        listView.setAdapter(adapter);
        listView.setItemsCanFocus(true);

        Button completeWorkoutButton = (Button) findViewById(R.id.btn_completeWorkout);
        completeWorkoutButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutSession.toggleCompleted();
                finish();
            }
        });
    }

    /**
     * Gets a list of all WorkoutSessionExercises in the workout session being viewed
     * @return a list of WorkoutSessionExercises in the current workout session
     */
    private List<WorkoutSessionExercise> getExercisesInWorkoutSession(){
        LocalDate workoutSessionDate;
        Intent intent;
        InterfaceAccessWorkoutSessions aws = (InterfaceAccessWorkoutSessions) new AccessWorkoutSessions();
        List<WorkoutSessionExercise> retList = new ArrayList();

        //get workout session date
        intent = getIntent();
        workoutSessionDate = formatter.parseLocalDate(intent.getStringExtra("workoutSessionDate"));

        //get WorkoutSession from db
        workoutSession = (WorkoutSession) aws.getWorkoutSession(workoutSessionDate);

        //fetch all exercises from workout session
        Enumeration<WorkoutSessionExercise> exercises = workoutSession.getExerciseEnumeration();
        while(exercises.hasMoreElements()){
            retList.add(exercises.nextElement());
        }

        return retList;
    }

    /**
     * A custom extension of the ArrayAdapter class, used for displaying exercise name and quantity
     * along with a checkbox beside it in a ListView
     */
    private class CheckboxAdapter extends ArrayAdapter {
        private List<WorkoutSessionExercise> exerciseList;
        Context context;

        /**
         * A constructor for a CheckboxAdapter, which is a custom ArrayAdapter used for displaying
         * exercise name and quantity along with a checkbox of whether it's been completed
         * @param context The activity's context
         * @param resourceId the layout resource used for the adapter
         * @param exerciseList a list of exercises in the workout session
         */
        public CheckboxAdapter(Context context, int resourceId, List<WorkoutSessionExercise> exerciseList) {
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
            CheckBox completed;
        }


        /**
         * Returns a view containing the workout session exercise name and quantity along with a checkbox
         * for each exercise in the workout session
         * @param index the index of the exercise in the exercise list
         * @param convertView the view used for conversion
         * @param parent the parent ViewGroup
         * @return a view containing the workout session exercise name and quantity along with a
         * checkbox for each exercise in the workout session
         */
        @Override
        public View getView(final int index, View convertView, @NonNull ViewGroup parent) {
            ViewHolder viewHolder;

            final View returnedView;

            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_workout_session_exercise, parent, false);
                viewHolder.exerciseName = (TextView) convertView.findViewById(R.id.textView);
                viewHolder.exerciseQuantity = (TextView) convertView.findViewById(R.id.textView2);
                viewHolder.completed = (CheckBox)convertView.findViewById(R.id.checkbox);

                returnedView = convertView;
                convertView.setTag(viewHolder);
            } else {
                viewHolder= (ViewHolder) convertView.getTag();
                returnedView = convertView;
            }

            WorkoutSessionExercise sessionExercise = exerciseList.get(index);

            viewHolder.exerciseName.setText(sessionExercise.getName());
            viewHolder.exerciseQuantity.setText(sessionExercise.getRecommendedQuantity().toString());
            viewHolder.completed.setChecked(exerciseList.get(index).isComplete());
            viewHolder.completed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    exerciseList.get(index).toggleCompleted();

                    if (isChecked) {
                        numCompletedExercises++;
                    } else {
                        numCompletedExercises--;
                    }

                    Button completeWorkoutButton = (Button) findViewById(R.id.completeWorkoutButton);
                    if(numCompletedExercises == workoutSession.numExercises()) {
                        //all exercises in workout session are completed, enable button
                        completeWorkoutButton.setEnabled(true);
                        completeWorkoutButton.setClickable(true);
                    } else {
                        //not all exercises in workout session are completed, disable button
                        completeWorkoutButton.setEnabled(false);
                        completeWorkoutButton.setClickable(false);
                    }
                }
            });
            return returnedView;
        }
    }
}
