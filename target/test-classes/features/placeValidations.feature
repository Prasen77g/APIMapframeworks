Feature: Validating Place API's
@AddPlace @Regression
Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI

    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" with "POST" http request
    Then The API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And Verify place_Id created maps to "<name>" using "getPlaceAPI"

Examples:
      |name      |language  |address|
      |Aubin     |English   |World Cross Center|
 #    |Bramwell  |German    |Sea Cross Center|
 #    |Conran    |Spanish   |Universe Cross Center|
@DeletePlace @Regression
Scenario: Verify if Delete Place functionality is working

	Given DeletePlace Payload
	When User calls "deletePlaceAPI" with "POST" http request
	Then The API call got success with status code 200
	And "status" in response body is "OK"	