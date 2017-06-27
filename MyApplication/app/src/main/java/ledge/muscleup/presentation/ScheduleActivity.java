package ledge.muscleup.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
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

import java.util.List;

import ledge.muscleup.R;
import ledge.muscleup.business.AccessWorkoutSessions;
import ledge.muscleup.model.schedule.ScheduleWeek;
import ledge.muscleup.model.workout.WorkoutSession;

/**
 * ScheduleActivity displays a list of workout sessions
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-07
 */
public class ScheduleActivity extends Activity {

    private ListItemAdapter adapter;

	private AccessWorkoutSessions aws;
    private ScheduleWeek scheduleWeek;
    private List<WorkoutSession> sessionList;

    private static final DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        aws = new AccessWorkoutSessions();
        scheduleWeek = new ScheduleWeek(aws.getCurrentWeekSessions());


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_list_display);

        populateList();

    }

    private void populateList(){
        ListView listView = (ListView) findViewById(R.id.list_panel);
        sessionList = scheduleWeek.getWorkoutSessionList();

        adapter = new ListItemAdapter(getApplicationContext(), R.layout.list_item_workout_session, sessionList);
        listView.setAdapter(adapter);

        setupListeners(sessionList);

        setupNavButtons();
    }

    /**
     * Creates listeners for navigation buttons
     */
    private void setupNavButtons(){
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
    }


    /**
     * Creates listeners for each workout in the schedule
     * @param workoutSessionList list of workouts to display for currently selected week
     */
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

    /**
     * clears current list and repopulates with next week's scheduled workouts
     */
    public void showNextWeek(){
        adapter.clear();

        aws.nextWeek(scheduleWeek);
        sessionList = scheduleWeek.getWorkoutSessionList();

        populateList();
    }

    /**
     * clears current list and repopulates with last week's scheduled workouts
     */
    public void showLastWeek(){
        adapter.clear();

        aws.lastWeek(scheduleWeek);
        sessionList = scheduleWeek.getWorkoutSessionList();

        populateList();
    }

    /**
     * A custom extension of the ArrayAdapter class, used for displaying workout session date and name
     */
    private class ListItemAdapter extends ArrayAdapter {
        private List<WorkoutSession> sessionList;
        Context context;

        /**
         * A constructor for a ListItemAdapter, which is a custom ArrayAdapter used for displaying
         * workout session date and name
         * @param context The activity's context
         * @param resourceId the layout resource used for the adapter
         * @param sessionList a list of workout sessions
         */
        public ListItemAdapter(Context context, int resourceId, List<WorkoutSession> sessionList) {
            super(context, resourceId, sessionList);
            this.sessionList = sessionList;
            this.context = context;
        }

        /**
         * A wrapper class holding the different elements of a single list item in the list view.
         * Contains 1 TextView for date and 1 TextView for name, as well as a remove button
         */
        private class ViewHolder {
            TextView sessionDate;
            TextView sessionWorkoutName;
            Button removeButton;
        }

        /**
         * Returns a view containing the workout session date and name
         * @param index the index of the session in the session list
         * @param convertView the view used for conversion
         * @param parent the parent ViewGroup
         * @return a view containing the workout session date and name
         */
        @Override
        public View getView(final int index, View convertView, @NonNull ViewGroup parent) {
            ViewHolder viewHolder;

            final View returnedView;

            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_workout_session, parent, false);
                viewHolder.sessionDate = (TextView) convertView.findViewById(R.id.scheduleDate);
                viewHolder.sessionWorkoutName = (TextView) convertView.findViewById(R.id.scheduleWorkoutName);
                viewHolder.removeButton = (Button) convertView.findViewById(R.id.removeWorkoutSessionButton);

                returnedView = convertView;
                convertView.setTag(viewHolder);
            } else {
                viewHolder= (ViewHolder) convertView.getTag();
                returnedView = convertView;
            }

            WorkoutSession session = sessionList.get(index);

            viewHolder.sessionDate.setText(formatter.print(session.getDate()));
            viewHolder.sessionWorkoutName.setText(session.getName());
            viewHolder.removeButton.setText("X");

            viewHolder.removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    aws.removeWorkoutSession(sessionList.get(index));
                    WorkoutSession emptySession = new WorkoutSession(sessionList.get(index).getDate());
                    sessionList.set(index, emptySession);
                    notifyDataSetChanged();
                }
            });

            return returnedView;
        }
    }
}
