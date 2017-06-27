package ledge.muscleup.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

import ledge.muscleup.R;
import ledge.muscleup.business.AccessWorkoutSessions;
import ledge.muscleup.model.schedule.ScheduledWeek;
import ledge.muscleup.model.workout.WorkoutSession;

/**
 * ScheduleActivity displays a list of workout sessions
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-07
 */
public class ScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ScheduledWeek scheduledWeek = new ScheduledWeek(new AccessWorkoutSessions());
        ListManager lm = new ListManager();
        List<WorkoutSession> scheduleArray;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_display);

        scheduleArray = scheduledWeek.getWorkoutSessionList();

        lm.populateList(this, scheduleArray);
        setupListeners(scheduleArray);
    }

    private void setupListeners(final List<WorkoutSession> workoutSessionList) {
        ListView list = (ListView) findViewById(R.id.list_panel);

        //will respond to clicks by opening a WorkoutSessionActivity and passing the date of the session clicked
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent appInfo = new Intent(ScheduleActivity.this, WorkoutSessionActivity.class);
                LocalDate date = workoutSessionList.get(position).getDate();
                DateTimeFormatter  formatter= DateTimeFormat.forPattern("MM/dd/yyyy");

                appInfo.putExtra("workoutSessionDate", formatter.print(date));
                startActivity(appInfo);
            }
        });
    }
}
