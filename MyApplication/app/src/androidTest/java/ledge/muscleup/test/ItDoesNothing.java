package ledge.muscleup.test;

import ledge.muscleup.presentation.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class ItDoesNothing extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public ItDoesNothing() {
		super(MainActivity.class);
  	}

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
		solo.clickOnButton("Workouts");
		solo.goBack();
		solo.clickOnButton("Workout Schedule");
		solo.goBack();
		solo.clickOnButton("Exercises");
		solo.goBack();

	}
}
