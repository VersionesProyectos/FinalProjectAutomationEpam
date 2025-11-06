Feature: Test Login

  Scenario: Test Login form with empty credentials

    Given the user is on the login page
    When the user enters a username and password
    And the user clears both fields and clicks the login button
    Then the message "Epic sadface: Username is required" should appear


  Scenario: Test login form with credentials by providing only the username
    Given the user is on the login page
    When the user types any credentials in the username field and enters a password
    And the user clears the password input and clicks the login button
    Then the message "Epic sadface: Password is required" should appear


  Scenario: Test login form with valid username and password
    Given the user is on the login page
    When the user types valid credentials (username and password)
    And the user clicks the "Login" button
    Then the user should be redirected to the dashboard