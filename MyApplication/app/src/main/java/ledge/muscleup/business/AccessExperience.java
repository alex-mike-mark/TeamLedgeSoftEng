package ledge.muscleup.business;

import org.joda.time.LocalDate;

import ledge.muscleup.application.Services;
import ledge.muscleup.persistence.DataAccessStub;

/**
 * Created by koope on 2017-06-28.
 */

public class AccessExperience implements InterfaceAccessExperience {
    private DataAccessStub dataAccess;

    public AccessExperience() {
        dataAccess = (DataAccessStub) Services.getDataAccess();
    }

    public int getExperienceTotalOnDate(LocalDate date) {
        return dataAccess.getExperienceTotalOnDate(date);
    }

    public void addExperienceTotal(LocalDate date, int experienceTotal) {
        dataAccess.addExperienceTotal(date, experienceTotal);
    }
}
