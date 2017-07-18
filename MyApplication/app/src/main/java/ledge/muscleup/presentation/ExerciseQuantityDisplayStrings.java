package ledge.muscleup.presentation;

import java.text.DecimalFormat;
import java.util.HashMap;

import ledge.muscleup.model.exercise.ExerciseDistance;
import ledge.muscleup.model.exercise.ExerciseDuration;
import ledge.muscleup.model.exercise.ExerciseSets;
import ledge.muscleup.model.exercise.ExerciseSetsAndWeight;
import ledge.muscleup.model.exercise.InterfaceExerciseQuantity;


/**
 * ExerciseQuantityDisplayStrings is a class used for converting exercise quantities to a string
 * for display. This class contains a map with an entry for each type of exercise quantity and it's
 * format string, as well as a method for retrieving the display string for a passed in quantity
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-07-10
 */

final class ExerciseQuantityDisplayStrings {
    private static final HashMap<Class, String> formatStringMap;

    static {
        formatStringMap = new HashMap<>();
        formatStringMap.put(ExerciseDistance.class, "%.2f %s");
        formatStringMap.put(ExerciseDuration.class, "%d %s");
        formatStringMap.put(ExerciseSetsAndWeight.class, "%d sets of %d reps (%.1f %s)");
        formatStringMap.put(ExerciseSets.class, "%d sets of %d reps");
    }

    /**
     * Given an exercise quantity as a parameter, uses the map to generate and return a properly
     * formatted string for that exercise quantity
     * @param qty the exercise quantity to generate a string for display for
     * @return a formatted string for displaying the exercise quantity on screen
     */
    public static String getExerciseQuantityDisplayString(InterfaceExerciseQuantity qty) {
        String formatString = formatStringMap.get(qty.getClass());
        String displayString;

        if (qty instanceof ExerciseDistance) {
            ExerciseDistance distance = (ExerciseDistance) qty;
            displayString = String.format(formatString, distance.getDistance(), String.valueOf(distance.getUnitOfMeasure()));
        } else if (qty instanceof ExerciseDuration) {
            ExerciseDuration duration = (ExerciseDuration) qty;
            displayString = String.format(formatString, duration.getTime(), String.valueOf(duration.getUnitOfMeasure()));
        }  else if (qty instanceof ExerciseSetsAndWeight) {
            ExerciseSetsAndWeight setsAndWeight = (ExerciseSetsAndWeight) qty;
            displayString = String.format(formatString, setsAndWeight.getSets(), setsAndWeight.getReps(), setsAndWeight.getWeight(), String.valueOf(setsAndWeight.getUnitOfMeasure()));
        } else if (qty instanceof ExerciseSets) {
            ExerciseSets sets = (ExerciseSets) qty;
            displayString = String.format(formatString, sets.getSets(), sets.getReps());
        } else {
            displayString = null;
        }

        return displayString;
    }
}
