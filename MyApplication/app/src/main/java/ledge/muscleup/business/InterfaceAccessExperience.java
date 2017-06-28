package ledge.muscleup.business;

import org.joda.time.LocalDate;

/**
 * Created by koope on 2017-06-28.
 */

public interface InterfaceAccessExperience {
    int getExperienceTotalOnDate(LocalDate date);
    void addExperienceTotal(LocalDate date, int experienceTotal);
}
