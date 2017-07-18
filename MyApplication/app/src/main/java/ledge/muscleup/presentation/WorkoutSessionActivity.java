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

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
public class WorkoutSessionActivity extends Activity {

    private WorkoutSession workoutSession;  //the workout session in view
    private ListItemAdapter adapter;
    private static final DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy");

    /**
     *  onCreate initializes WorkoutSessionActivity
     * @param savedInstanceState contains context from last activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final InterfaceAccessWorkoutSessions aws = new AccessWorkoutSessions();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_session);

        ListView listView = (ListView) findViewById(R.id.checklist);
        final List<WorkoutSessionExercise> exerciseList = getExercisesInWorkoutSession();

        String dateString = formatter.print(workoutSession.getDate());
        TextView sessionDateTextView = (TextView) findViewById(R.id.workoutSessionDate);
        sessionDateTextView.setText(dateString);

        TextView sessionNameTextView = (TextView) findViewById(R.id.workoutSessionName);
        sessionNameTextView.setText(workoutSession.getName());

        adapter = new ListItemAdapter(getApplicationContext(), R.layout.list_item_workout_session_exercise, exerciseList);

        listView.setAdapter(adapter);
        listView.setItemsCanFocus(true);

        Button completeWorkoutButton = (Button) findViewById(R.id.btn_completeWorkout);
        if(workoutSession.isComplete()) {
            TextView completedTextView = (TextView) findViewById(R.id.workoutSessionIsCompleted);
            completedTextView.setVisibility(View.VISIBLE);
            completeWorkoutButton.setText("Back");
            completeWorkoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(WorkoutSessionActivity.this, ScheduleActivity.class));
                }
            });
        } else {
            if (workoutSession.getDate().isAfter(LocalDate.now())) {
                completeWorkoutButton.setText("Back");
                completeWorkoutButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(WorkoutSessionActivity.this, ScheduleActivity.class));
                    }});
            } else if (workoutSession.getDate().isBefore(LocalDate.now().minusWeeks(1))) {
                TextView expiredTextView = (TextView) findViewById(R.id.workoutSessionIsExpired);
                expiredTextView.setVisibility(View.VISIBLE);
                completeWorkoutButton.setText("Back");
                completeWorkoutButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(WorkoutSessionActivity.this, ScheduleActivity.class));
                    }});
            } else {
                completeWorkoutButton.setText("Complete");
                completeWorkoutButton.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        aws.toggleWorkoutCompleted(workoutSession);
                        Intent appInfo = new Intent(WorkoutSessionActivity.this, CompletedWorkoutActivity.class);
                        LocalDate date = workoutSession.getDate();
                        appInfo.putExtra("workoutSessionDate", formatter.print(date));
                        startActivity(appInfo);
                    }
                });
            }
        }

    }

    /**
     * Overrides the onBackPressed method to jump to the ScheduleActivity whenever back button is pressed here
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(WorkoutSessionActivity.this, ScheduleActivity.class));
    }


    /**
     * Gets a list of all WorkoutSessionExercises in the workout session being viewed
     * @return a list of WorkoutSessionExercises in the current workout session
     */
    private List<WorkoutSessionExercise> getExercisesInWorkoutSession(){
        LocalDate workoutSessionDate;
        Intent intent;
        InterfaceAccessWorkoutSessions aws = new AccessWorkoutSessions();

        //get workout session date
        intent = getIntent();
        workoutSessionDate = formatter.parseLocalDate(intent.getStringExtra("workoutSessionDate"));
        workoutSession = aws.getWorkoutSession(workoutSessionDate);
        return workoutSession.getWorkoutSessionExercises();
    }

    /**
     * A custom extension of the ArrayAdapter class, used for displaying exercise name and quantity
     *
     */
    private class ListItemAdapter extends ArrayAdapter {
        private List<WorkoutSessionExercise> exerciseList;
        Context context;

        /**
         * A constructor for a ListItemAdapter, which is a custom ArrayAdapter used for displaying
         * exercise name and quantity
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
         * A wrapper class holding the different elements of a single list item in the list view
         */
        private class ViewHolder {
            TextView exerciseName;
            TextView exerciseQuantity;
        }


        /**
         * Returns a view containing the workout session exercise name and quantity
         * @param index the index of the exercise in the exercise list
         * @param convertView the view used for conversion
         * @param parent the parent ViewGroup
         * @return a view containing the workout session exercise name and quantity
         */
        @Override
        public View getView(final int index, View convertView, @NonNull ViewGroup parent) {
            ViewHolder viewHolder;

            final View returnedView;

            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_workout_session_exercise, parent, false);
                viewHolder.exerciseName = (TextView) convertView.findViewById(R.id.workoutSessionExerciseName);
                viewHolder.exerciseQuantity = (TextView) convertView.findViewById(R.id.workoutSessionExerciseQuantity);

                returnedView = convertView;
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                returnedView = convertView;
            }

            WorkoutSessionExercise sessionExercise = exerciseList.get(index);

            viewHolder.exerciseName.setText(sessionExercise.getName());
            String exerciseQuantity = ExerciseQuantityDisplayStrings.getExerciseQuantityDisplayString(
                    sessionExercise.getRecommendedQuantity()
            );
            viewHolder.exerciseQuantity.setText(exerciseQuantity);

            return returnedView;
        }
    }
}
