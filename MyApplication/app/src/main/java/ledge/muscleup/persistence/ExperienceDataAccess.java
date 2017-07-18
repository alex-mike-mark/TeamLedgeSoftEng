package ledge.muscleup.persistence;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ledge.muscleup.model.experience.CompletedWorkoutRecord;

/**
 * The data access class for experience data
 *
 * @author Cole Kehler
 * @version 3.0
 * @since 2017-07-13
 */
public class ExperienceDataAccess implements InterfaceExperienceDataAccess {
    private Statement statement;
    private ResultSet resultSet;

    /**
     * Opens the ExperienceDataAccess
     * @param statement the statement to use in ExperienceDataAccess queries
     */
    public void open(Statement statement) {
        this.statement = statement;
    }

    /**
     * Close the ExperienceDataAccess
     */
    public void close() {
        try {
            statement.close();
        }
        catch (Exception e) {
            DataAccess.sqlError(e);
        }
    }

    /**
     * Returns the list of all completed workout records
     *
     * @return a list of all completed workout records
     */
    @Override
    public List<CompletedWorkoutRecord> getCompletedWorkouts() {
        List<CompletedWorkoutRecord> completedWorkoutRecordList = new ArrayList<>();
        CompletedWorkoutRecord completedWorkoutRecord = null;
        String workoutName = null;
        LocalDateTime loggedDate = null;
        int currentXP = -1;
        int previousXP;

        try
        {
            resultSet = statement.executeQuery(
                    "SELECT         W.Name, " +
                    "               PH.LoggedDate, " +
                    "               PH.CurrentXP " +
                    "FROM           ProgressHistory PH " +
                    "LEFT JOIN      WorkoutSessions WS " +
                    "               ON PH.WorkoutSessionID = WS.ID " +
                    "LEFT JOIN      Workouts W " +
                    "               ON WS.WorkoutID = W.ID " +
                    "ORDER BY       PH.LoggedDate DESC ");

            while (resultSet.next())
            {
                previousXP = resultSet.getInt("CurrentXP");

                if (workoutName != null) {
                    completedWorkoutRecord = new CompletedWorkoutRecord(workoutName, previousXP, currentXP, loggedDate);
                    completedWorkoutRecordList.add(completedWorkoutRecord);
                }

                workoutName = resultSet.getString("Name");
                loggedDate = new LocalDateTime(resultSet.getDate("LoggedDate"));
                currentXP = previousXP;
            }

            if (workoutName != null) {
                completedWorkoutRecord = new CompletedWorkoutRecord(workoutName, 0, currentXP, loggedDate);
                completedWorkoutRecordList.add(completedWorkoutRecord);
            }

            resultSet.close();
        }
        catch (Exception e) {
            DataAccess.sqlError(e);
        }

        return completedWorkoutRecordList;
    }

    /**
     * Returns the most recent completed workout
     *
     * @return the most recent completed workout
     */
    @Override
    public CompletedWorkoutRecord getMostRecentCompletedWorkout() {
        CompletedWorkoutRecord completedWorkoutRecord = null;
        String workoutName;
        LocalDateTime loggedDate;
        int currentXP;
        int previousXP = 0;

        try {
            resultSet = statement.executeQuery(
                    "SELECT TOP 2   W.Name, " +
                    "               PH.LoggedDate, " +
                    "               PH.CurrentXP " +
                    "FROM           ProgressHistory PH " +
                    "LEFT JOIN      WorkoutSessions WS " +
                    "               ON PH.WorkoutSessionID = WS.ID " +
                    "LEFT JOIN      Workouts W " +
                    "               ON WS.WorkoutID = W.ID " +
                    "ORDER BY       PH.LoggedDate DESC ");

            if (resultSet.next())
            {
                workoutName = resultSet.getString("Name");
                loggedDate = new LocalDateTime(resultSet.getDate("LoggedDate"));
                currentXP = resultSet.getInt("CurrentXP");

                if (resultSet.next())
                    previousXP = resultSet.getInt("CurrentXP");

                completedWorkoutRecord = new CompletedWorkoutRecord(workoutName, previousXP, currentXP, loggedDate);
            }

            resultSet.close();
        }
        catch (Exception e) {
            DataAccess.sqlError(e);
        }

        return completedWorkoutRecord;
    }
}
