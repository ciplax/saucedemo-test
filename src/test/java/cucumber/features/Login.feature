Feature: Login Functionality

  Scenario Outline: Ensure Login Functionality
    Given user is on saucedemo login page
    When user input <username> as username
    And user input <password> as password
    And user click login
    Then user verify <status> login result
    Examples:
      | username | password | status |
      | standard_user | secret_sauce | success |
      | locked_out_user | secret_sauce | failed |
      | random_user | secret_sauce | failed |
