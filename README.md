# Activity for Futures

## Instructions

In package controllers, each package is an activity. You should implement both methods in each activity
The first method should be completed using `map` and `flatMap` only
The second method should be completed using a `for` - `yield` comprehension
Both methods should output the same result.
The methods to use to get this data are in the connector wrapped in futures. These methods represent calls to an API for data.

### Activity 1 - User Details
Complete both methods. The expected result is: `"Luke Skywalker - Jedi - 0"`


### Activty 2 - Stock Price
In this activity you should infer the behaviour of the methods from the unit tests.
Remember, both methods should do the same thing using different syntax.
The purpose of this activity is to explore error handling for `Future.failed`

## Running
To run this, just use `sbt test`