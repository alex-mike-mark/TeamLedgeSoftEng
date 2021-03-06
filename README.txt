This is the submission for iteration 3 of MuscleUp.
MuscleUp is an app intended to help people create and stick to their fitness goals. 
The app is intended to feature a variety of exercises and workouts, the ability to create custom workouts, schedule sessions and gamify their progress.
Created by: Cole Kehler, Ryan Koop, Matt Smidt, Alex Mark and Jon Ingram

###Git Repository###
https://github.com/kerosenecity/TeamLedgeSoftEng

###Contents of this .zip###
This readme plus our source code. The log is found in a google doc linked far below.
Also contained is our endless ambitions for a future society in which all lifeforms feel inspired to MuscleUp, though they don't compress so well.

###Packages###
	main
		assets
			db
				MU_DB.script
		java
			ledge.muscleup
				application
					Services (Performs set up tasks. Currently linking db to app)
					Main
				business
					AccessExercises (performs actions on database regarding exercises)
					AccessWorkouts  (performs actions on database regarding workouts)
					AccessWorkoutSessions
					AccessExperience
					InterfaceAccessExercises
					InterfaceAccessWorkouts
					InterfaceAccessWorkoutSessions
					InterfaceAccessExperience
				model
					exercise
							enums
								DistanceUnit
								ExerciseIntensity 
								ExerciseType
								TimeUnity
								WeightUnit
						Exercise (Stores data relating to exercises)
						ExerciseDistance
						ExerciseDuration 
						ExerciseSets
						ExerciseSetsAndWeight
						InterfaceExerciseDistance 
						InterfaceExerciseDuration
						InterfaceExerciseQuantity
						InterfaceExerciseSets
						InterfaceExerciseSetsAndWeight
						WorkoutExercise (An Execise wrapped for appropriate use by a Workout)
						WorkoutExerciseDistance
						WorkoutExerciseDuration
						WorkoutExerciseSets
						WorkoutExerciseSetsAndWeight
						WorkoutSessionExercise (A WorkoutExercise wrapped for appropriate use by a WorkoutSession)
					experience
						CompletedWorkoutRecord
						ExperienceHistory
						LevelProgress
					schedule
						ScheduleWeek (Manages a week of scheduled workouts)
					workout
						Workout (Contains a set of WorkoutExercises. Used to make WorkoutSessions)
						WorkoutSession (Contains a set of WorkoutSessionExercises, used to track actual workouts)
				persistence
					DataAccess
					ExerciseDataAccess
					ExperienceDataAccess
					InterfaceDataAccess
					InterfaceDataAccessComponent
					InterfaceExerciseDataAccess
					InterfaceWorkoutDataAccess
					InterfaceWorkoutSessionDataAccess
					WorkoutDataAccess
					WorkoutSesssionDataAccess
				presentation
					CompleteWorkoutActivity
					ExerciseActivity
					ExerciseQuantityDisplayStrings (a class used for converting exercise quantities to proper presentation strings)
					Main Activity
					ProgressReportActivity
					ScheduleActivity
					WorkoutActivity
					WorkoutDetailsActivity
					WorkoutSessionActivity
				MuscleUpApplication (sets up JODA for date, time tracking)
	test
		ledge.muscleup (test)
			integration
				BusinessPersistenceSeamTest
				DataAccessHSQLDBTest
			model.unit.business
				TemplateDataAccessStub
			business
				AccessExercisesTest
				AccessExperienceTest
				AccessWorkoutSessionsTest
				AccessWorkoutsTest
				BusinessTests
			model
				exercise
					ExerciseDistanceTest
					ExerciseDurationTest
					ExerciseQuantityTest
					ExerciseSetsAndWeightTest
					ExerciseSetsTest
					ExerciseTest
					WorkoutExerciseDurationTest
					WorkoutExerciseSubsTest
					WorkoutExerciseTest
					WorkoutSessionExerciseTest
				experience
					CompletedWorkoutRecordTest
					ExperienceHistoryTest
					LevelProgressTest
				schedule
					ScheduleWeekTest
				workout
					WorkoutSessionTest
					WorkoutTest
				ModelTests
			persistence
				ExerciseDataAccessTest
				ExperienceDataAccessTest
				PersistenceTests
				TemplateDataAccessStub
				WorkoutDataAccessTest
				WorkoutSessionDataAccessTest
			AllTests
			IntegrationTests
			RunIntegrationTests
			RunUnitTests
		
		ledge.muscleup.test (androidTest)
			AllAcceptanceTests
			ExerciseAcceptanceTest
			ProgressReportAcceptanceTest
			SuggestedAcceptanceTest
			TodayAcceptanceTest
			WorkoutAcceptanceTest
			WorkoutScheduleAcceptanceTest

###Log Location###
The log for iteration 1 is in this google doc: <https://docs.google.com/document/d/18dOXb27PLIrS7kjHcLGODmWes66gZQpesqNNg8ew2Go/edit>
The log for iteration 2 is in this google doc: <https://docs.google.com/document/d/1U3ojEM5CPwwEfbJQhXPyOEgiolTkioJ4-ktY7Vx_Tt4/edit>
The log for iteration 3 is in this google doc: <https://docs.google.com/document/d/1YRsWGrI4YPK1E0Yw9NCT6QUgqCC_UO3N1V3NLjMwXoY/edit>

###Canonical copy of database file###
app/src/main/assets/db/MU_DB.script

###New Features and Where to Find Them###
-The major features implemented in this release are:
-The ability to gain experience points and levels from completing workouts
-The ability to view a current progress report of experience gained, level, and recently completed workouts in Progress Report
-The ability to quickly navigate to today's scheduled workout right from the main menu screen
-Provide users with a daily suggested workout on the main menu screen, and the ability to view this suggested workout by clicking on
 the suggested workout
-The ability to navigate straight to the schedule when viewing the contents of a workout in Workouts

###Known Code Smells###
-Builder pattern- We investigated applying the Builder pattern, however after we ended up removing some parameters from constructors
		  that weren't necessary due to changed/axed features (such as isFavourite), we found that the majority of our
		  constructors were much smaller and the objects were much easier to build than previously. While there is still one
		  constructor that is quite large, and takes in 5 parameters, we found that all the parameters were necessary and
		  it didn't make sense to make it such that some of the parameters could be specified. For these reasons, we decided
		  not to apply the builder pattern in the end, as we felt it was not necessary.

###Known Issues###
-The Robotium acceptance tests are extending a deprecated method "ActivityInstrumentationTestCase2" but we were unable to find a 
		replacement in the given time frame