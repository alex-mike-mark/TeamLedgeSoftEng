package ledge.muscleup.persistence;

import org.joda.time.LocalDate;

/**
 * Created by koope on 2017-06-28.
 */

public interface InterfaceExperienceDataAccess {
     void addExperienceTotal(LocalDate date, int experienceTotal);
    int getExperienceTotalOnDate(LocalDate date);
}
