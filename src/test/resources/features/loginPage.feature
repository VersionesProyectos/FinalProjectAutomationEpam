Feature: Test Login

  As a user
  I want to verify login functionality
  So that I can ensure valid and invalid login cases are handled correctly

  Scenario Outline: Validate login scenarios
    Given the user is on the login page
    When the user types username "<username>" and password "<password>"
    And the user performs the action "<action>"
    Then the user should see the message "<message>"
    And the login task should comply with:
      | UC-1 |
      | UC-2 |
      | UC-3 |


    Examples:
      | username      | password      | action                             | message                                 | UC  |
      | error         | error123      | clear both fields and click login  | Epic sadface: Username is required     | UC-1 |
      | error         | error123      | clear password and click login     | Epic sadface: Password is required     | UC-2 |
      | standard_user | secret_sauce  | click login button                 | User is redirected to the dashboard    | UC-3 |


