package ledge.muscleup.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import ledge.muscleup.R;
import ledge.muscleup.application.Services;
import ledge.muscleup.business.AccessWorkouts;
import ledge.muscleup.business.InterfaceAccessWorkouts;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.workout.Workout;
import ledge.muscleup.persistence.InterfaceDataAccess;

/**
 * WorkoutDetailsActivity displays a list of exercises for a workout
 *
 * @author Jon Ingram
 * @version 1.0
 * @since 2017-06-04
 */

public class WorkoutDetailsActivity extends Activity {

    /**
     *  onCreate initializes WorkoutDetailsActivity
     * @param savedInstanceState contains context from last activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListManager lm = new ListManager();
        List exerciseList = new ArrayList();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_display);

        exerciseList = getExcersiseList();

        lm.populateList(this, exerciseList);

        TextView filter = (TextView) findViewById(R.id.filter_title);
        filter.setText("Filter: none");
    }

    /**
     * getExerciseList will get the workout that was clicked on in WorkoutActivity and return the exercises associated
     * @return list of exercises for a workout
     */
    private List getExcersiseList(){
        String workoutName;
        Intent intent;
        Workout workout;
        InterfaceAccessWorkouts aw = (InterfaceAccessWorkouts) new AccessWorkouts();
        List retList = new ArrayList();

        //get name of workout
        intent = getIntent();
        workoutName = intent.getStringExtra("workoutName");

        //get Workout from db
        workout = (Workout) aw.getWorkout(workoutName);

        //fetch all exercises from workout
        Enumeration<WorkoutExercise> exercises = workout.getExerciseEnumeration();
        while(exercises.hasMoreElements()){
            retList.add(exercises.nextElement());
        }

        return retList;
    }
}
