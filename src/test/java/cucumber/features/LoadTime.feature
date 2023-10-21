Feature: ResponseTime Acceptability

  Scenario Outline: Ensure Pages area loaded within acceptable time
    Given user is on saucedemo login page
    When user input <username> as username
    And user input <password> as password
    And start timer
    And user click login
    And count total loadtime
    Then user verify <status> login result
    And total load time after login is under <acceptable_response_time_seconds>
    Examples:
      | username | password | status | acceptable_response_time_seconds |
      | standard_user | secret_sauce | success | 3              |
      | performance_glitch_user | secret_sauce | success | 3    |
