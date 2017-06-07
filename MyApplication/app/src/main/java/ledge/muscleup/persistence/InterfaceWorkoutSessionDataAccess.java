package ledge.muscleup.persistence;

import org.joda.time.LocalDate;

import java.util.List;

import ledge.muscleup.model.workout.InterfaceWorkoutSession;

/**
 * An interface for workout session database access, including methods for retrieving, inserting,
 * and removing workout sessions
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-07
 */
public interface InterfaceWorkoutSessionDataAccess {
    public List<InterfaceWorkoutSession> getWorkoutSessionsList();
    public InterfaceWorkoutSession getWorkoutSession(LocalDate dateOfSession);
    public void insertWorkoutSession(InterfaceWorkoutSession workoutSession);
    public void removeWorkoutSession(InterfaceWorkoutSession workoutSession);
}
