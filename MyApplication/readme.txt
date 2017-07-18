This is the submission for iteration 1 of MuscleUp.
MuscleUp is an app intended to help people create and stick to their fitness goals. The app is intended to feature a variety of exercises, the ability to create custom workouts, schedule sessions and gamify their progress.

###Contents of this .zip###
[WHATEVER GOES HERE]

###Nice Packages###
	main
		java
			ledge.muscleup
				application
					Services (Performs set up tasks. Currently linking db to app)
				business
					AccessExercises (performs actions on database regarding exercises)
					AccessWorkouts  (performs actions on database regarding workouts)
				model
					exercise
						DistanceUnit (enum for units. Used in ExerciseDistance, Duration, Sets and SetsAndWeight classes)
						Exercise (Stores and returns information about exercises)
						ExerciseDistance (One of the quantities. WorkoutExercise contains one to plan workouts.)
						ExerciseDuration 
						ExerciseIntensity (enum for categorizing an exercise's stress on the human body.)
						ExerciseSets (One of the quantities. WorkoutExercise contains one to plan workouts.)
						ExerciseSetsAndWeight (One of the quantities. WorkoutExercise contains one to plan workouts.)
						ExerciseType (enum for tracking how an exercise works the body.)
						InterfaceExercise
						InterfaceExerciseDistance 
						InterfaceExerciseDuration
						InterfaceExerciseQuantity
						InterfaceExerciseSets
						InterfaceExerciseSetsAndWeight
						InterfaceWorkoutExercise
						InterfaceWorkoutSessionExercise
						WeightUnit
						WorkoutExercise
						WorkoutSessionExercise
					workout
						InterfaceWorkout
						InterfaceWorkoutSession
						Workout
						WorkoutSession
				persistence
					DataAccess
					DataAccessStub
				presentation
					Main Activity 
					WorkoutActivity
				MuscleUpApplication (sets up JODA for date, time tracking)
	test
		ledge.muscleup.model
			AllTests
			ExampleUnitTest
			ExerciseDistanceTest
			ExerciseDurationTest
			ExerciseQuantityTest
			ExerciseSetsTest
			ExerciseTest
			WorkoutTest

The application is divided into five packages: application, business, presentation, persistence and model. The model package is divided further into two parts; workout and exercise.

The application package contains the class Services and nothing else. Services is run on startup and connects the database to the app.

The business package contains the code for accessing the database. The classes AccessExercises and AccessWorkouts have functions for adding, removing and getting name data from exercises and workouts in the database.

The model package contains the class definitions.
###Log Location###
The log is found in [PLACE WHERE THE LOG IS FOUND].

###New Features and Where to Find Them###
The major features implemented in this release are:
The ability to view a list of Workouts, found by mashing the workouts button on the dashboard.
The ability to view a list of Exercises, found by mashing the exercises button on the dashboard.
The ability to view details of workouts, found by mashing one of the workouts in the workouts list view.
The ability to view your workout schedule, found by mashing the workout schedule button on the dashboard.