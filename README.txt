This is the submission for iteration 1 of MuscleUp.
MuscleUp is an app intended to help people create and stick to their fitness goals. The app is inteded to feature a variety of exercises, the ability to create custom workouts, schedule sessions and gamify their progress.

###Contents of this .zip###
This readme plus our source code. The log is found in a google doc linked far below.
Also contained is our blood, sweat and tears, though they don't compress so well.

###Packages###
	main
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
					InterfaceScheduleManager
					ScheduleManager (Manages and tracks WorkoutSessions)
				model
					exercise
						DistanceUnit
						Exercise (Stores data relating to exercises)
						ExerciseDistance
						ExerciseDuration 
						ExerciseIntensity 
						ExerciseSets
						ExerciseSetsAndWeight
						ExerciseType
						InterfaceExerciseDistance 
						InterfaceExerciseDuration
						InterfaceExerciseQuantity
						InterfaceExerciseSets
						InterfaceExerciseSetsAndWeight
						WeightUnit
						WorkoutExercise (An Execise wrapped for appropriate use by a Workout)
						WorkoutSessionExercise (A WorkoutExercise wrapped for appropriate use by a WorkoutSession)
					workout
						Workout (Contains a set of WorkoutExercises. Used to make WorkoutSessions)
						WorkoutSession (Contains a set of WorkoutSessionExercises, used to track actual workouts)
				persistence
					DataAccessStub
					InterfaceDataAccess
					InterfaceExerciseDataAccess
					InterfaceWorkoutDataAccess
					InterfaceWorkoutSessionDataAccess
				presentation
					ExerciseActivity
					ListManager
					Main Activity
					ScheduleActivity
					WorkoutActivity
					WorkoutDetailsActivity
				MuscleUpApplication (sets up JODA for date, time tracking)
	test
		ledge.muscleup.model
			AllTests
			ExampleUnitTest
			ExerciseDistanceTest
			ExerciseDurationTest
			ExerciseQuantityTest
			ExerciseSetsTest
			ListedExerciseTest
			ModifiableWorkoutTest
			ScheduleManagerTest
			TemplateDataAccessStub

###Log Location###
The log is in this google doc: <https://docs.google.com/document/d/18dOXb27PLIrS7kjHcLGODmWes66gZQpesqNNg8ew2Go/edit>

###New Features and Where to Find Them###
The major features implemented in this release are:
The ability to view a list of Workouts, found by mashing the workouts button on the dashboard.
The ability to view a list of Exercises, found by mashing the exercises button on the dashboard.
The ability to view details of workouts, found by mashing one of the workouts in the workouts list view.
The ability to view your workout schedule, found by mashing the workout schedule button on the dashboard.