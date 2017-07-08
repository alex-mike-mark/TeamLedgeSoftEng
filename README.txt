This is the submission for iteration 2 of MuscleUp.
MuscleUp is an app intended to help people create and stick to their fitness goals. 
The app is intended to feature a variety of exercises and workouts, the ability to create custom workouts, schedule sessions and gamify their progress.
Created by: Cole Kehler, Ryan Koop, Matt Smidt, Alex Mark and Jon Ingram

###Git Repository###
https://github.com/kerosenecity/TeamLedgeSoftEng

###Contents of this .zip###
This readme plus our source code. The log is found in a google doc linked far below.
Also contained is our hopes, dreams and endless passion for working out, though they don't compress so well.

###Packages###
	main
		assets
			db
				MU_DB.backup
				MU_DB.script
				restoreDefaultDatabase.bat
		java
			ledge.muscleup
				application
					Services (Performs set up tasks. Currently linking db to app)
				business
					AccessExercises (performs actions on database regarding exercises)
					AccessWorkouts  (performs actions on database regarding workouts)
					AccessWorkoutSessions 
					InterfaceAccessExercises
					InterfaceAccessWorkouts
					InterfaceAccessWorkoutSessions
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
					schedule
						ScheduleWeek (Manages a week of scheduled workouts)
					workout
						Workout (Contains a set of WorkoutExercises. Used to make WorkoutSessions)
						WorkoutSession (Contains a set of WorkoutSessionExercises, used to track actual workouts)
				persistence
					DataAccess
					DataAccessStub
					InterfaceDataAccess
					InterfaceExerciseDataAccess
					InterfaceWorkoutDataAccess
					InterfaceWorkoutSessionDataAccess
				presentation
					CompleteWorkoutActivity
					ExerciseActivity
					Main Activity
					ScheduleActivity
					WorkoutActivity
					WorkoutDetailsActivity
					WorkoutSessionActivity
				MuscleUpApplication (sets up JODA for date, time tracking)
	test
		ledge.muscleup.model
			business
				AccessExercisesTest
					AccessExercisesTest
					TemplateAccessExercises
				AccessWorkoutSessions
				AccessWorkoutsTest
					AccessWorkoutsTest
					TemplateAccessWorkouts
				TemplateDataAccessStub
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
			workout
				WorkoutSessionTest
				WorkoutTest
			AllTests
			ScheduleManagerTest.java
				ScheduleManagerTest
				TemplateAccessWorkoutSessions
				TemplateDataAccessStub

###Log Location###
The log for iteration 1 is in this google doc: <https://docs.google.com/document/d/18dOXb27PLIrS7kjHcLGODmWes66gZQpesqNNg8ew2Go/edit>
The log for iteration 2 is in this google doc: <https://docs.google.com/document/d/1U3ojEM5CPwwEfbJQhXPyOEgiolTkioJ4-ktY7Vx_Tt4/edit>

###New Features and Where to Find Them###
The major features implemented in this release are:
New and improved look and feel
The ability to view and navigate a weekly view under Scheduled Workouts (press "next" or "previous")
The ability to jump to the current date when navigating Scheduled Workouts (press "current week" button)
The ability to add and remove scheduled workouts in Scheduled Workouts (press "+" or "x")
The ability to complete a scheduled workout and view experience points gained (check all exercises and click "Complete Workout")

###Known Code Smells###
-The builder pattern should be used to help construct objects 
-The presentation layer is calling toString() methods for exercise quantities
-The queries we have written for accessing our database are potentially insecure, as no measures have yet been taken to prevent SQL injection. 

We are aware that these code smells still exist, and thus realize we have technical debt, but did not have time to fix these up in Iteration
2. Our plan is to make fixing these smells up a priority early in Iteration 3.
