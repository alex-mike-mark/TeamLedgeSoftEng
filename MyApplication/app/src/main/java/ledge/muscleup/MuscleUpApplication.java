package ledge.muscleup;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * The application, for which the only change needed is to initialize libraries when the application
 * is created
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-05
 */

final class MuscleUpApplication extends Application {
    /**
     * Called when the application is starting, and calls the default application {@code onCreate()}
     * and then initializes any necessary libraries
     */
    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
    }
}
