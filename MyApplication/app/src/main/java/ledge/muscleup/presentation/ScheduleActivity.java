package ledge.muscleup.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.List;

import ledge.muscleup.R;
import ledge.muscleup.application.Services;
import ledge.muscleup.business.AccessWorkoutSessions;
import ledge.muscleup.business.InterfaceAccessWorkoutSessions;
import ledge.muscleup.business.InterfaceScheduleManager;
import ledge.muscleup.business.ScheduleManager;
import ledge.muscleup.model.workout.WorkoutSession;
import ledge.muscleup.persistence.DataAccessStub;
import ledge.muscleup.persistence.InterfaceDataAccess;

/**
 * ScheduleActivity displays a list of workout sessions
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-07
 */
public class ScheduleActivity extends AppCompatActivity {
    private List<WorkoutSession> scheduleArray;
    private ListManager lm;
    private InterfaceScheduleManager scheduleManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        scheduleManager = new ScheduleManager(new AccessWorkoutSessions());
        lm = new ListManager();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_list_display);

        final Button nextBtn = (Button) findViewById(R.id.button_next);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showNextWeek();
            }
        });

        final Button prevBtn = (Button) findViewById(R.id.button_previous);
        prevBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showLastWeek();
            }
        });

        scheduleArray = scheduleManager.getWorkoutSessionList();

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

    public void showNextWeek(){
        lm.clearList();

        scheduleManager.nextWeek();
        scheduleArray = scheduleManager.getWorkoutSessionList();

        lm.populateList(this, scheduleArray);
        setupListeners(scheduleArray);
    }

    public void showLastWeek(){
        lm.clearList();

        scheduleManager.lastWeek();
        scheduleArray = scheduleManager.getWorkoutSessionList();

        lm.populateList(this, scheduleArray);
        setupListeners(scheduleArray);
    }
}
