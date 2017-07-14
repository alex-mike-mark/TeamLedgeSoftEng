package ledge.muscleup.application;

import ledge.muscleup.business.InterfaceAccessWorkoutSessions;
import ledge.muscleup.persistence.DataAccess;
import ledge.muscleup.persistence.ExerciseDataAccess;
import ledge.muscleup.persistence.ExperienceDataAccess;
import ledge.muscleup.persistence.InterfaceDataAccess;
import ledge.muscleup.persistence.InterfaceExerciseDataAccess;
import ledge.muscleup.persistence.InterfaceExperienceDataAccess;
import ledge.muscleup.persistence.InterfaceWorkoutDataAccess;
import ledge.muscleup.persistence.InterfaceWorkoutSessionDataAccess;
import ledge.muscleup.persistence.WorkoutDataAccess;
import ledge.muscleup.persistence.WorkoutSessionDataAccess;

/**
 * A class containing static methods for creating, retrieving, and closing the DataAccess.
 * The methods in this class also ensure that only one DataAccess is created
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-05
 */
public class Services {
    private static InterfaceDataAccess dataAccessService = null;
    private static InterfaceExerciseDataAccess exerciseDataAccessService = null;
    private static InterfaceExperienceDataAccess experienceDataAccessService = null;
    private static InterfaceWorkoutDataAccess workoutDataAccessService = null;
    private static InterfaceWorkoutSessionDataAccess workoutSessionDataAccessService = null;

    /**
     * Creates and opens the database, if it hasn't already been created
     * @param dbName the name of the database
     */
    public static void createDataAccess(String dbName) {
        if (dataAccessService == null) {
            dataAccessService = new DataAccess(dbName);
            exerciseDataAccessService = new ExerciseDataAccess();
            experienceDataAccessService = new ExperienceDataAccess();
            workoutDataAccessService = new WorkoutDataAccess();
            workoutSessionDataAccessService = new WorkoutSessionDataAccess();

            dataAccessService.open(Main.getDBPathName());
            exerciseDataAccessService.open(dataAccessService.getNewStatement());
            experienceDataAccessService.open(dataAccessService.getNewStatement());
            workoutDataAccessService.open(dataAccessService.getNewStatement());
            workoutSessionDataAccessService.open(dataAccessService.getNewStatement());

        }
    }

    /**
     * Creates and opens the database, using an alternative database implementation
     * @param altDataAccessService the alternative database implementation
     */
    public static void createDataAccess(InterfaceDataAccess altDataAccessService,
                                        InterfaceExerciseDataAccess altExerciseDataAccessService,
                                        InterfaceExperienceDataAccess altExperienceDataAccess,
                                        InterfaceWorkoutDataAccess altWorkoutDataAccess,
                                        InterfaceWorkoutSessionDataAccess altWorkoutSessionDataAccess) {
        if (dataAccessService == null) {
            dataAccessService = altDataAccessService;
            exerciseDataAccessService = altExerciseDataAccessService;
            experienceDataAccessService = altExperienceDataAccess;
            workoutDataAccessService = altWorkoutDataAccess;
            workoutSessionDataAccessService = altWorkoutSessionDataAccess;

            dataAccessService.open(Main.getDBPathName());
            exerciseDataAccessService.open(dataAccessService.getNewStatement());
            experienceDataAccessService.open(dataAccessService.getNewStatement());
            workoutDataAccessService.open(dataAccessService.getNewStatement());
            workoutSessionDataAccessService.open(dataAccessService.getNewStatement());
        }
    }

    /**
     * Gets the class which gives access to exercise data
     * @return access to exercise data
     */
    public static InterfaceExerciseDataAccess getExerciseDataAccess() {
        if (dataAccessService == null)
            handleUninitializedDB();
        return exerciseDataAccessService;
    }

    /**
     * Gets the class which gives access to workout data
     * @return access to workout data
     */
    public static InterfaceWorkoutDataAccess getWorkoutDataAccess() {
        if (dataAccessService == null)
            handleUninitializedDB();
        return workoutDataAccessService;
    }

    /**
     * Gets the class which gives access to workout session data
     * @return access to workout session data
     */
    public static InterfaceWorkoutSessionDataAccess getWorkoutSessionDataAccess() {
        if (dataAccessService == null)
            handleUninitializedDB();
        return workoutSessionDataAccessService;
    }

    /**
     * Gets the class which gives access to experience data
     * @return access to experience data
     */
    public static InterfaceExperienceDataAccess getExperienceDataAccess() {
        if (dataAccessService == null)
            handleUninitializedDB();
        return experienceDataAccessService;
    }

    /**
     * Closes the database connection, if the database exists. Sets the dataAccessService variable
     * to null
     */
    public static void closeDataAccess() {
        if (dataAccessService != null) {
            dataAccessService.close();
            exerciseDataAccessService.close();
            dataAccessService = null;
        }
    }

    /**
     * Handles cases where attempts are made to modify an uninitialized database
     */
    private static void handleUninitializedDB() {
        System.out.println("Connection to data access has not been established");
        System.exit(1);
    }
}
