package ledge.muscleup.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import ledge.muscleup.R;
import ledge.muscleup.business.AccessWorkoutSessions;
import ledge.muscleup.business.InterfaceScheduleManager;
import ledge.muscleup.business.ScheduleManager;

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
        InterfaceScheduleManager scheduleManager = new ScheduleManager(new AccessWorkoutSessions());
        ListManager lm = new ListManager();
        List scheduleArray;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_display);

        scheduleArray = scheduleManager.getWorkoutSessionList();

        lm.populateList(this, scheduleArray);
    }

}
