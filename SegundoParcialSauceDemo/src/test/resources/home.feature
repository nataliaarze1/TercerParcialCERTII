Feature: Home page

  Background: User login into Sauce Demo
    Given I am in sauce demo web page
    When I set the user name text box with "standard_user"
    And I set the password text box with "secret_sauce"
    And I click on the login button


  @run
  Scenario: Verify the filter high to low
      When The home page should be displayed
      And I click on the filter Price high to low



  @run
    Scenario: Verify the filter Name Z to A
      When The home page should be displayed
      And I click on the filter Name Z to A

  @run
    Scenario Outline: Verify social media icons
      When The home page should be displayed
      And I click on the "<socialMedia>" icon
      Then Social Media "<socialMedia>" account must be opened
      Examples:
        | socialMedia |
        | Facebook    |
        | Twitter     |
        | LinkedIn    |

