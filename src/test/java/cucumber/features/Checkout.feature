Feature: Checkout Functionality

  Scenario Outline: Checkout with items in cart, input information details
    Given user is on logged in using standard_user
#    When user add cart sauce labs backpack to cart
    And user click cart icon
    And user click checkout button
    And user fill "<first_name>" and "<last_name>" and <postal_code>
    And user click continue
    Then user verify checkout result <status>
    Examples:
      | first_name | last_name | postal_code | status |
      | dudi       | idud      | 12345       | success |
      |            |           | 12345       | error |
