package ledge.muscleup.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import ledge.muscleup.R;
import ledge.muscleup.business.AccessExperience;
import ledge.muscleup.business.InterfaceAccessExperience;
import ledge.muscleup.model.experience.CompletedWorkoutRecord;
import ledge.muscleup.model.experience.ExperienceHistory;


/**
 * ProgressReportActivity displays various metrics of the user's progress, including their current
 * level, progress towards next level, number of workouts completed and experience gained in the
 * last week and month, and a list of recently completed workouts
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-07-07
 */
public class ProgressReportActivity extends Activity {
    private ListItemAdapter adapter;
    private static final InterfaceAccessExperience ae = new AccessExperience();
    private static final DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd");
    /**
     *  onCreate initializes WorkoutDetailsActivity
     * @param savedInstanceState contains context from last activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_report);

        ExperienceHistory experienceHistory = new ExperienceHistory(ae.getCompletedWorkouts());
        TextView currLevelTextView = (TextView) findViewById(R.id.currentLevel);

        String currLevelString = "LEVEL " + experienceHistory.getCurrLevel();
        currLevelTextView.setText(currLevelString);

        TextView xpNeededTextView = (TextView) findViewById(R.id.nextLevelXPNeeded);
        int xpGained = experienceHistory.getNextLevelXPProgress();
        int xpNeeded = experienceHistory.getNextLevelXPTotal();
        String xpString = "Level Up: " + xpGained + " XP / " + xpNeeded + " XP";
        xpNeededTextView.setText(xpString);

        ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar);
        double progressPercentage = (double) xpGained / xpNeeded * 100;
        bar.setProgress((int) progressPercentage);

        int numCompletedLast7Days = experienceHistory.getNumWorkoutsCompleted(7);
        TextView completedLast7DaysTextView = (TextView) findViewById(R.id.numCompletedLast7Days);
        completedLast7DaysTextView.setText(String.valueOf(numCompletedLast7Days));

        int numCompletedLast30Days = experienceHistory.getNumWorkoutsCompleted(30);
        TextView completedLast30DaysTextView = (TextView) findViewById(R.id.numCompletedLast30Days);
        completedLast30DaysTextView.setText(String.valueOf(numCompletedLast30Days));

        int xpGainedLast7Days = experienceHistory.getXPGained(7);
        TextView xpGainedLast7DaysTextView = (TextView) findViewById(R.id.xpGainedLast7Days);
        xpGainedLast7DaysTextView.setText(String.valueOf(xpGainedLast7Days));

        int xpGainedLast30Days = experienceHistory.getXPGained(30);
        TextView xpGainedLast30DaysTextView = (TextView) findViewById(R.id.xpGainedLast30Days);
        xpGainedLast30DaysTextView.setText(String.valueOf(xpGainedLast30Days));

        int levelsGainedLast7Days = experienceHistory.getLevelsGained(7);
        TextView levelsGainedLast7DaysTextView = (TextView) findViewById(R.id.levelsGainedLast7Days);
        levelsGainedLast7DaysTextView.setText(String.valueOf(levelsGainedLast7Days));

        int levelsGainedLast30Days = experienceHistory.getLevelsGained(30);
        TextView levelsGainedLast30DaysTextView = (TextView) findViewById(R.id.levelsGainedLast30Days);
        levelsGainedLast30DaysTextView.setText(String.valueOf(levelsGainedLast30Days));

        Enumeration<CompletedWorkoutRecord> enumeration = experienceHistory.getCompletedWorkoutsEnumeration();
        final int numRecent = 3;
        int count = 0;
        List<CompletedWorkoutRecord> recentlyCompleted = new ArrayList<>();
       while (enumeration.hasMoreElements() && count < numRecent) {
            recentlyCompleted.add(enumeration.nextElement());
            count++;
        }

        adapter = new ListItemAdapter(getApplicationContext(), R.layout.list_item_progress_report, recentlyCompleted);
        ListView listView = (ListView) findViewById(R.id.recentlyCompletedWorkouts);
        listView.setAdapter(adapter);
    }

    /**
     * Overrides the onBackPressed method to jump to the MainActivity whenever back button is pressed here
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ProgressReportActivity.this, MainActivity.class));
    }

    /**
     * A custom extension of the ArrayAdapter class, used for displaying date workout was completed,
     * completed workout name, and xp gained from completed workout
     */
    private class ListItemAdapter extends ArrayAdapter {
        private List<CompletedWorkoutRecord> recordList;
        Context context;

        /**
         * A constructor for a ListItemAdapter, which is a custom ArrayAdapter used for displaying
         * date workout was completed, completed workout name, and xp gained from completed workout
         * @param context The activity's context
         * @param resourceId the layout resource used for the adapter
         * @param recordList a list of workout sessions
         */
        public ListItemAdapter(Context context, int resourceId, List<CompletedWorkoutRecord> recordList) {
            super(context, resourceId, recordList);
            this.recordList = recordList;
            this.context = context;
        }

        /**
         * A wrapper class holding the different elements of a single list item in the list view.
         * Contains 1 TextView for completed date , 1 TextView for completed workout name, and 1
         * TextView for xpGained
         */
        private class ViewHolder {
            TextView completedDate;
            TextView completedWorkoutName;
            TextView xpGained;
        }

        /**
         * Returns a view containing the workout session date and name
         * @param index the index of the session in the session list
         * @param convertView the view used for conversion
         * @param parent the parent ViewGroup
         * @return a view containing the date workout was completed, completed workout name,
         * and xp gained from completed workout
         */
        @Override
        public View getView(final int index, View convertView, @NonNull ViewGroup parent) {
            final ListItemAdapter.ViewHolder viewHolder;

            final View returnedView;

            if (convertView == null) {
                viewHolder = new ListItemAdapter.ViewHolder();
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_progress_report, parent, false);
                viewHolder.completedDate = (TextView) convertView.findViewById(R.id.completedDate);
                viewHolder.completedWorkoutName = (TextView) convertView.findViewById(R.id.completedWorkoutName);
                viewHolder.xpGained = (TextView) convertView.findViewById(R.id.xpGainedFromWorkout);

                returnedView = convertView;
                convertView.setTag(viewHolder);
            } else {
                viewHolder= (ListItemAdapter.ViewHolder) convertView.getTag();
                returnedView = convertView;
            }

            CompletedWorkoutRecord currRecord = recordList.get(index);

            viewHolder.completedDate.setText(formatter.print(currRecord.getDateOfCompletion()));
            viewHolder.completedWorkoutName.setText(currRecord.getWorkoutName());
            viewHolder.xpGained.setText("+" + String.valueOf(currRecord.getExperienceGained()) + " XP");

            return returnedView;
        }
    }
}
