Feature: User Signup
  As a user,
  I want to sign up with a username and password
  So that I can create an account and access the system.

  Scenario: Successful signup with valid inputs
    Given I have entered a valid username "john_doe"
    And I have entered a valid password "password123"
    When I submit the signup form
    Then my account should be created successfully

  Scenario: Signup fails with invalid inputs
    Given I have entered an invalid username ""
    And I have entered an invalid password "short"
    When I submit the signup form
    Then I should see an error message "Invalid username or password"