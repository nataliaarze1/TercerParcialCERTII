Feature: Checkout
  Background: Verify yhe Checkout Page
    Given I am in sauce demo web page
    When I set the user name text box with "standard_user"
    And I set the password text box with "secret_sauce"
    And I click on the login button


  @run
  Scenario: Verify checkout summary page details
    And I add to the cart the product "Sauce Labs Onesie"
    And I click on the cart button
    And I click on the checkout button
    And I fill the checkout information with
      | First Name | Last Name | Postal Code |
      | Natalia    | Arze      | 00000       |
    And I click on continue button
    And The checkout summary page should display the correct details for
      | Sauce Labs Onesie |

  @run
    Scenario: Verify that an error is displayed when the first name is missing during checkout
      Given The home page should be displayed
      And I add to the cart the product "Sauce Labs Backpack"
      And I add to the cart the product "Sauce Labs Bolt T-Shirt"
      And I click on the cart button
      And I click on the checkout button
      And I fill the checkout information with
        | First Name | Last Name | Postal Code |
        |            | Arze      | 00000       |
      And I click on continue button
      Then A message that says "Error: First Name is required" should be displayed

