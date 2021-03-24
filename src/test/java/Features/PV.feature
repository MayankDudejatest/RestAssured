Feature: Validating API's

Scenario: Verify if place API is there
	Given AddPlace API
	When user calls "AddPlaceAPI" with post API
	Then APIclass throws message
	And "status" is "OK"
	And "scope" is "APP"

