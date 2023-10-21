Feature: Inventory Functionality

  Scenario Outline: Ensure Inventory Page Images Loaded Correctly
    Given user is on saucedemo login page
    When user input <username> as username
    And user input <password> as password
    And user click login
    Then user verify <status> login result
    And user can view <is_image> images
    Examples:
      | username | password | status | is_image |
      | standard_user | secret_sauce | success | correct |
      | problem_user | secret_sauce | success | wrong  |
