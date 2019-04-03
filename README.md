# Activity for Futures

## Instructions

In package controllers, each package is an activity. You should implement both methods in each activity
The first method should be completed using `map` and `flatMap` only
The second method should be completed using a `for` - `yield` comprehension
Both methods should output the same result.
The methods to use to get this data are in the connector wrapped in futures. These methods represent calls to an API for data.

### Activity 1 - User Details
Complete both methods. The expected result is: `"Luke Skywalker - Jedi - 0"`


### Activity 2 - Stock Price
In this activity you should infer the behaviour of the methods from the unit tests.
Remember, both methods should do the same thing using different syntax.
The purpose of this activity is to explore error handling for `Future.failed`

### Activity 3 - Pet Shop
You have been asked by a pet shop to make some changes to some of their code which gets the price for some of their pets.
You have been told there is an API available through the `PetConnector` to retrieve information from their database.
This API has the functionality to retrieve a pet given an ID and retrieve a price given a Pet.
However, this API has an issue:
Whenever the shop hosts an event to give away some of their pets, the `getPrice` call on the API throws a `PetNotFoundException`. The owners would like it so that whenever this issue occurs, the price is returned as 0, but any other exception should be **logged** and still be thrown.
Furthermore, the pet shop sometimes likes to put a 10% sale on. They have a flag included in their code for the discount. Whenever this is true, the end price should be reduced by 10%.

The method implemented to address this issue should be completed twice. One using maps and flatMaps, the other using for yield syntax.

## Running
To run this, just use `sbt test`