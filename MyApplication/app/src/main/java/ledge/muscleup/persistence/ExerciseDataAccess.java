package ledge.muscleup.persistence;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.enums.ExerciseIntensity;
import ledge.muscleup.model.exercise.enums.ExerciseType;

/**
 * The data access class for exercise data
 *
 * @author Cole Kehler
 * @version 3.0
 * @since 2017-07-13
 */

public class ExerciseDataAccess implements InterfaceExerciseDataAccess {
    private Statement statement;
    private ResultSet resultSet;

    public void open(Statement statement) {
        this.statement = statement;
    }

    public void close() {
        try {
            statement.close();
        }
        catch (Exception e) {
            DataAccess.sqlError(e);
        }
    }

    /**
     * Gets a list of all exercises in the database
     *
     * @return a list of all exercises in the database
     */
    @Override
    public List<Exercise> getExercisesList() {
        List<Exercise> exerciseList = new ArrayList<>();
        String name;
        ExerciseIntensity intensity;
        ExerciseType type;

        try {
            //get the exercises from the db
            resultSet = statement.executeQuery(
                    "SELECT     E.Name, " +
                    "           EI.Intensity, " +
                    "           ET.Type " +
                    "FROM       Exercises E " +
                    "LEFT JOIN  ExerciseIntensities EI " +
                    "           ON E.IntensityID = EI.ID " +
                    "LEFT JOIN  ExerciseTypes ET " +
                    "           ON E.TypeID = ET.ID");

            while (resultSet.next()) {
                //build an exercise from the query results
                name = resultSet.getString("Name");
                intensity = ExerciseIntensity.valueOf(resultSet.getString("Intensity"));
                type = ExerciseType.valueOf(resultSet.getString("Type"));

                exerciseList.add(new Exercise(name, intensity, type));
            }
            resultSet.close();
        }
        catch (Exception e) {
            DataAccess.sqlError(e);
        }

        return exerciseList;
    }
}
