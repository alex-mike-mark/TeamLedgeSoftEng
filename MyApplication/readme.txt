This is the submission for iteration 1 of MuscleUp.
MuscleUp is an app intended to help people create and stick to their fitness goals. The app is inteded to feature a variety of exercises, the ability to create custom workouts, schedule sessions and gamify their progress.

###Contents of this .zip###
[WHATEVER GOES HERE]

###Nice Packages###
	main
		java
			ledge.muscleup
				application
					Services (Performs set up tasks. Currently linking db to app)
				business
					AccessExercises
					AccessWorkouts
				model
					exercise
						DistanceUnit
						Exercise
						ExerciseDistance 
						ExerciseDuration
						ExerciseIntensity
						ExerciseSets
						ExerciseSetsAndWeight
						WorkoutExercise
						WorkoutSessionExercise
						InterfaceExercise
						InterfaceExerciseDistance 
						InterfaceExerciseDuration
						InterfaceExerciseIntensity
						InterfaceExerciseSetsAndReps
						InterfaceExerciseWeight
						WeightUnit
						WorkoutExercise
						WorkoutSessionExercise
					workout
						Workout
						WorkoutSession
				persistence
					DataAccess
					DataAccessStub
				presentation
					Main Activity 
					WorkoutActivity
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
The ability to view a list of Exercises,