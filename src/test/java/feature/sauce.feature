Feature: Verify whether user can add item to cart

  Scenario: Verify whether user one can add item to cart
    Given user opens the browser
    And navigates to Sauce-demo login page
    When User enters the username and password of user one and click on login button
    And Verify the sorting
    And user add item to cart and do checkout
    Then user should do the final checkout
