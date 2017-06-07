package ledge.muscleup.persistence;

/**
 * The interface for the database stub, which implements several other interfaces for data access
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-07
 */

public interface InterfaceDataAccessStub extends InterfaceDataAccess, InterfaceExerciseDataAccess,
        InterfaceWorkoutDataAccess, InterfaceWorkoutSessionDataAccess {
}
