Feature: Booking Template
  Background: Booking endpoints should allow to get, create, update and delete employees

  Scenario:/booking should return all the employees
    Given I perform a GET call the booking endpoint
    Then I verify that the status code is 200
    And verify that the body does not have size 0

  Scenario:/booking/{id} should return an specific booking
    Given I perform a GET call to the bookings endpoint with id "1"
    Then I verify that the status code is 200
    And The booking has the firstname field
    And The booking has the lastname field
    And The booking has the total price field
    And The booking has the deposit paid field


  @run
  Scenario: /create should create a booking
    Given I perform a POST call to the create booking endpoint with the following data
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Natalia   | Arze     | 700        | true        | 2023-10-16 | 2024-12-24 | Breakfast       |
    Then I verify that the status code is 200
    And verify that the body does not have size 0


  Scenario: /booking/{id} should delete an specific booking
    Given I perform a DELETE call to the bookings endpoint with id "16"
    Then I verify that the status code is 201
    And The response message must be "created"


  @run
  Scenario: /booking/{id} should update an specific endpoint
    Given I perform a PUT call to the booking endpoint with id "25"
    Then I verify that the status code is 200
    And verify that the body does not have size 0

