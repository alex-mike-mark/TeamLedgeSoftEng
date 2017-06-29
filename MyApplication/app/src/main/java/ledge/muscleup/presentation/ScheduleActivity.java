package ledge.muscleup.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import ledge.muscleup.model.schedule.ScheduleWeek;
import ledge.muscleup.business.AccessWorkouts;
import ledge.muscleup.business.InterfaceAccessWorkouts;
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
    private InterfaceAccessWorkouts aw;

    private static final DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd");
    private static final DateTimeFormatter monthDayYearFormatter = DateTimeFormat.forPattern("MM/dd/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        aws = new AccessWorkoutSessions();

        scheduleWeek = new ScheduleWeek(aws.getCurrentWeekSessions());

        aw = new AccessWorkouts();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_list_display);

        populateList();
        setWeekRangeTitle();
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

        final Button currentWeekBtn = (Button) findViewById(R.id.button_currentWeek);
        currentWeekBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showCurrentWeek();
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
     * Overrides the onBackPressed method to jump to the MainActivity whenever the back button is pressed here
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ScheduleActivity.this, MainActivity.class));
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
                if (workoutSessionList.get(position).getName() != null) {   //workout scheduled on day
                    Intent appInfo = new Intent(ScheduleActivity.this, WorkoutSessionActivity.class);
                    LocalDate date = workoutSessionList.get(position).getDate();
                    DateTimeFormatter  formatter= DateTimeFormat.forPattern("MM/dd/yyyy");

                    appInfo.putExtra("workoutSessionDate", formatter.print(date));
                    startActivity(appInfo);
                }
            }
        });
    }

    /**
     * clears current list and repopulates with next week's scheduled workouts
     */
    public void showNextWeek(){
        adapter.clear();

        aws.setToNextWeek(scheduleWeek);
        sessionList = scheduleWeek.getWorkoutSessionList();

        populateList();
        setWeekRangeTitle();
    }

    /**
     * clears current list and repopulates with last week's scheduled workouts
     */
    public void showLastWeek(){
        adapter.clear();

        aws.setToLastWeek(scheduleWeek);
        sessionList = scheduleWeek.getWorkoutSessionList();

        populateList();
        setWeekRangeTitle();
    }

    public void showCurrentWeek() {
        adapter.clear();
        aws.setToCurrentWeek(scheduleWeek);
        sessionList = scheduleWeek.getWorkoutSessionList();

        populateList();
        setWeekRangeTitle();
    }

    /**
     * Sets the Activity title shown on screen to display the range of dates for current week
     */
    public void setWeekRangeTitle() {
        TextView titleView = (TextView) findViewById(R.id.activity_title);
        LocalDate startOfWeek = sessionList.get(0).getDate();
        LocalDate endOfWeek = sessionList.get(sessionList.size() - 1).getDate();
        String title = monthDayYearFormatter.print(startOfWeek) + " - " + monthDayYearFormatter.print(endOfWeek);
        titleView.setText(title);
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
            TextView sessionCompleted;
            Button addOrRemoveButton;
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
            final ViewHolder viewHolder;

            final View returnedView;

            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_workout_session, parent, false);
                viewHolder.sessionDate = (TextView) convertView.findViewById(R.id.scheduleDate);
                viewHolder.sessionWorkoutName = (TextView) convertView.findViewById(R.id.scheduleWorkoutName);
                viewHolder.addOrRemoveButton = (Button) convertView.findViewById(R.id.addOrRemoveWorkoutSessionButton);
                viewHolder.sessionCompleted = (TextView) convertView.findViewById(R.id.scheduleIsCompleted);

                returnedView = convertView;
                convertView.setTag(viewHolder);
            } else {
                viewHolder= (ViewHolder) convertView.getTag();
                returnedView = convertView;
            }

            WorkoutSession session = sessionList.get(index);

            viewHolder.sessionDate.setText(formatter.print(session.getDate()));
            viewHolder.sessionWorkoutName.setText(session.getName());

            if (session.isComplete()) { //display complete, hide add/remove button
                viewHolder.sessionCompleted.setVisibility(View.VISIBLE);
                viewHolder.addOrRemoveButton.setVisibility(View.INVISIBLE);
            } else { //hide complete, display add/remove button
                viewHolder.sessionCompleted.setVisibility(View.INVISIBLE);
                viewHolder.addOrRemoveButton.setVisibility(View.VISIBLE);
            }

            if (session.getName() == null) {//no workout scheduled, add button
                viewHolder.addOrRemoveButton.setText("+");
                viewHolder.addOrRemoveButton.setTextColor(Color.GREEN);
                viewHolder.addOrRemoveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu menu = new PopupMenu(ScheduleActivity.this, viewHolder.addOrRemoveButton);

                        List<String> workoutNames = aw.getWorkoutNamesList();
                        for (String name: workoutNames) {
                            menu.getMenu().add(name);

                        }
                        menu.show();
                        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                String workoutName = item.getTitle().toString();
                                WorkoutSession session = new WorkoutSession(aw.getWorkout(workoutName), sessionList.get(index).getDate(), false);
                                sessionList.set(index, session);
                                aws.insertWorkoutSession(session);
                                notifyDataSetChanged();
                                return true;
                            }
                        });
                    }
                });
            } else {
                viewHolder.addOrRemoveButton.setText("X");
                viewHolder.addOrRemoveButton.setTextColor(Color.RED);
                viewHolder.addOrRemoveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        aws.removeWorkoutSession(sessionList.get(index));
                        WorkoutSession emptySession = new WorkoutSession(sessionList.get(index).getDate());
                        sessionList.set(index, emptySession);
                        notifyDataSetChanged();
                    }
                });
            }

            return returnedView;
        }
    }
}
