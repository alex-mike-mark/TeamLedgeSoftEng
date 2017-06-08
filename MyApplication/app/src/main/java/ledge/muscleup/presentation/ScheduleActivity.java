package ledge.muscleup.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ledge.muscleup.R;
import ledge.muscleup.application.Services;
import ledge.muscleup.business.AccessWorkoutSessions;
import ledge.muscleup.business.InterfaceAccessWorkoutSessions;
import ledge.muscleup.business.InterfaceScheduleManager;
import ledge.muscleup.business.ScheduleManager;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        InterfaceScheduleManager scheduleManager = new ScheduleManager((InterfaceAccessWorkoutSessions) new AccessWorkoutSessions());
        ListManager lm = new ListManager();
        List scheduleArray;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_display);

        scheduleArray = scheduleManager.getWorkoutSessionList();

        lm.populateList(this, scheduleArray);
    }

}
